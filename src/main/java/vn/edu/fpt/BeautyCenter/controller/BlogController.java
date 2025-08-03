package vn.edu.fpt.BeautyCenter.controller;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE         Version  AUTHOR   DESCRIPTION
 * 2025-06-13   2.0      TrungBD  Blog Management with Advanced Filtering
 */

import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.fpt.BeautyCenter.dto.request.BlogRequest;
import vn.edu.fpt.BeautyCenter.dto.request.BlogFilterParams;
import vn.edu.fpt.BeautyCenter.dto.request.CommentRequest;
import vn.edu.fpt.BeautyCenter.dto.response.BlogResponse;
import vn.edu.fpt.BeautyCenter.dto.response.BlogCategoryResponse;
import vn.edu.fpt.BeautyCenter.dto.response.BlogTagResponse;
import vn.edu.fpt.BeautyCenter.dto.response.CommentResponse;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.entity.enums.BlogStatus;
import vn.edu.fpt.BeautyCenter.entity.enums.Role;
import vn.edu.fpt.BeautyCenter.exception.AppException;
import vn.edu.fpt.BeautyCenter.service.*;

import jakarta.validation.Valid;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Enhanced controller for managing blog posts with advanced filtering capabilities.
 * <p>
 * Provides comprehensive CRUD operations, advanced filtering, sorting, pagination,
 * rich text editing with Summernote, and tag/category management for blog system.
 * Features dropdown-based filters for status, date range, categories, tags selection,
 * and author filtering with enhanced security and validation.
 * </p>
 */
@Controller
@RequestMapping("/blogs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogController {

    BlogService blogService;
    BlogCategoryService blogCategoryService;
    BlogTagService blogTagService;
    UserService userService;
    NotificationService notificationService;
    S3Service s3Service;
    CommentService commentService;

    // List of allowed sort fields to prevent SQL injection
    private static final List<String> ALLOWED_SORT_FIELDS = Arrays.asList(
            "title", "author_id", "createdAt", "publishedAt", "viewCount", "status"
    );

    /**
     * Enhanced method to display blogs with comprehensive advanced filtering capabilities.
     * <p>
     * Supports multiple filter criteria including status, date range, categories,
     * tags selection, and author filtering, along with sorting and pagination.
     * Integrates seamlessly with dropdown-based filter UI components.
     * </p>
     *
     * @param page              current page number (default 0)
     * @param size              page size (default 10)
     * @param keyword           basic search keyword for blog title and content
     * @param status            blog status filter (DRAFT, PUBLISHED, ARCHIVED)
     * @param categoryId        category filter by category ID
     * @param fromDate          start date for creation date range filtering
     * @param toDate            end date for creation date range filtering
     * @param selectedTags      list of selected tags for categorical filtering
     * @param authorId          author filter by user ID
     * @param featured          filter for featured blogs only
     * @param sortBy            field to sort by (default "created_at")
     * @param sortDir           sort direction: "asc" or "desc" (default "desc")
     * @param session           current HTTP session for authentication
     * @param model             model to pass attributes to the view
     * @param redirectAttributes attributes for redirect scenarios with flash messages
     * @return the view name for the blog list with applied filters and data
     */
    @GetMapping({"", "/"})
    public String getAllBlogsWithAdvancedFilter(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "status", required = false) BlogStatus status,
            @RequestParam(name = "categoryId", required = false) Integer categoryId,
            @RequestParam(name = "fromDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(name = "toDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            @RequestParam(name = "selectedTags", required = false) List<String> selectedTags,
            @RequestParam(name = "authorId", required = false) String authorId,
            @RequestParam(name = "featured", required = false) Boolean featured,
            @RequestParam(name = "sortBy", defaultValue = "createdAt") String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "desc") String sortDir,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes) {

        try {
            // Validate and sanitize input parameters
            page = Math.max(0, page);
            size = Math.min(Math.max(1, size), 50);
            // Build comprehensive filter parameters object
            BlogFilterParams filterParams = BlogFilterParams.builder()
                    .keyword(sanitizeKeyword(keyword))
                    .status(status)
                    .categoryId(validateCategoryId(categoryId))
                    .fromDate(validateDate(fromDate))
                    .toDate(validateDate(toDate))
                    .selectedTags(sanitizeTags(selectedTags))
                    .authorId(sanitizeUserId(authorId))
                    .featured(featured)
                    .build();

            // Validate date range logic
            if (filterParams.getFromDate() != null && filterParams.getToDate() != null) {
                if (filterParams.getFromDate().isAfter(filterParams.getToDate())) {
                    notificationService.addWarningMessage(redirectAttributes,
                            "Start date cannot be after end date. Filters adjusted.");
                    filterParams.setFromDate(null);
                    filterParams.setToDate(null);
                }
            }
            Sort.Direction direction;
            // Create sort direction and validate sort field for security
            if(sortBy.equals("title")){
                direction = "desc".equalsIgnoreCase(sortDir) ?
                        Sort.Direction.ASC : Sort.Direction.DESC;
            }else{
            direction = "asc".equalsIgnoreCase(sortDir) ?
                    Sort.Direction.ASC : Sort.Direction.DESC;
            }
            String validatedSortBy = validateSortField(sortBy);
            Pageable pageable = PageRequest.of(page, size, Sort.by(direction, validatedSortBy));

            // Apply advanced filtering through service layer
            Page<BlogResponse> blogs = blogService.getBlogsWithAdvancedFilter(filterParams, pageable);

            // Enhance blog data with user names for display
            for (BlogResponse blog : blogs) {
                try {
                    String userName = userService.getAuthorNameById(blog.getAuthorId());
                    blog.setAuthorName(Objects.requireNonNullElse(userName, "Unknown User"));
                } catch (Exception e) {
                    blog.setAuthorName("Unknown User3");
                }
            }

            // Create a map of formatted tags for consistent display
            Map<String, String> tagsMap = blogs.getContent().stream()
                    .collect(Collectors.toMap(
                            BlogResponse::getBlogId,
                            blog -> formatTags(blog.getTags()),
                            (existing, replacement) -> existing
                    ));

            // Generate page numbers for pagination navigation
            if (blogs.getTotalPages() > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, blogs.getTotalPages())
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }

            // Prepare filter-related attributes for dropdown UI
            prepareFilterAttributes(model, filterParams);

            // Prepare filter button states for active indication
            prepareFilterButtonStates(model, filterParams);

            // Add main attributes to model for view rendering
            model.addAttribute("pageTitle", "Blog Management");
            model.addAttribute("blogs", blogs);
            model.addAttribute("formattedTags", tagsMap);
            model.addAttribute("filterParams", filterParams);
            model.addAttribute("sortBy", validatedSortBy);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("keyword", filterParams.getKeyword());
            model.addAttribute("hasActiveFilters", filterParams.hasActiveFilters());

            // Add blog statistics for dashboard insights
            addBlogStatistics(model, blogs);

            return "blogs/list";

        } catch (Exception e) {
            String errorMessage = "Error loading blogs: " +
                    (e.getMessage() != null ? e.getMessage() : "Unexpected error occurred");
            notificationService.addErrorMessage(redirectAttributes, errorMessage);
            System.err.println(errorMessage);
            model.addAttribute("pageTitle", "Blog Management");
            model.addAttribute("blogs", Page.empty());
            return "redirect:/blogs";
        }
    }

    /**
     * Displays the form for adding a new blog with rich text editor support.
     * <p>
     * Prepares form objects and available options for categories and tags.
     * Includes Summernote editor configuration and validation objects.
     * </p>
     *
     * @param model   model to pass attributes to the view
     * @param session current HTTP session for authentication
     * @return the view name for the add blog form
     */
    @GetMapping("/admin/add")
    public String showAddForm(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (isNotPermit(session)) {
            notificationService.addErrorMessage(redirectAttributes, "Access denied. Administrator privileges required.");
            return "redirect:/blogs";
        }

        try {
            // Add empty BlogRequest for form binding if not present
            if (!model.containsAttribute("blogRequest")) {
                model.addAttribute("blogRequest", new BlogRequest());
            }

            // Prepare dropdown data for form
            prepareFormDropdownData(model);

            model.addAttribute("pageTitle", "Add New Blog Post");
            return "blogs/add";

        } catch (Exception e) {
            notificationService.addErrorMessage(redirectAttributes,
                    "Error preparing add form: " + e.getMessage());
            return "redirect:/blogs";
        }
    }

    /**
     * Processes the creation of a new blog post with comprehensive validation.
     * <p>
     * Handles form validation, HTML content processing from Summernote,
     * tag management, and provides appropriate feedback through notification system.
     * </p>
     *
     * @param request            the blog creation request with form data
     * @param bindingResult      validation results from form binding
     * @param session            current HTTP session for user identification
     * @param redirectAttributes attributes for redirect scenarios with flash messages
     * @return redirect to appropriate page based on operation result
     */
    @PostMapping("/admin/add")
    public String saveBlog(@ModelAttribute("blogRequest") @Valid BlogRequest request,
                           BindingResult bindingResult,
                           HttpSession session,
                           RedirectAttributes redirectAttributes,
                           @RequestParam(value = "thumbnail") MultipartFile file) {
        if (isNotPermit(session)) {
            notificationService.addErrorMessage(redirectAttributes, "Access denied. Administrator privileges required.");
            return "redirect:/blogs";
        }

        // Handle validation errors
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.blogRequest", bindingResult);
            redirectAttributes.addFlashAttribute("blogRequest", request);
            notificationService.addErrorMessage(redirectAttributes,
                    "Please check the information entered. All required fields must be completed correctly.");
            return "redirect:/blogs/admin/add";
        }

        try {
            // Get current user for audit trail
            User user = (User) session.getAttribute("user");
            if (user == null) {
                notificationService.addErrorMessage(redirectAttributes, "Session expired. Please login again.");
                return "redirect:/blogs";
            }
            // Attempt to create the blog
            if (file != null && !file.isEmpty()) {
                try {
                    String url = s3Service.uploadFile(file);
                    request.setThumbnailUrl(url);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            BlogResponse createdBlog = blogService.createBlog(request, user.getUserId());

            // Success notification with blog title
            notificationService.addSuccessMessage(redirectAttributes,
                    "Blog post '" + request.getTitle() + "' has been successfully created!");

            return "redirect:/blogs";

        } catch (AppException e) {
            // Handle business logic exceptions
            notificationService.addErrorMessage(redirectAttributes, e.getMessage());
            redirectAttributes.addFlashAttribute("blogRequest", request);
            return "redirect:/blogs/admin/add";

        } catch (Exception e) {
            // Handle unexpected errors
            notificationService.addErrorMessage(redirectAttributes,
                    "An unexpected error occurred while creating the blog: " + e.getMessage());
            redirectAttributes.addFlashAttribute("blogRequest", request);
            return "redirect:/blogs/admin/add";
        }
    }

    /**
     * Displays the edit form for an existing blog with pre-populated data.
     * <p>
     * Retrieves blog details, formats them for form display with Summernote,
     * and prepares all necessary dropdown data for editing categories and tags.
     * </p>
     *
     * @param blogId             the ID of the blog to edit
     * @param model              model to pass attributes to the view
     * @param session            current HTTP session for authentication
     * @param redirectAttributes attributes for redirect scenarios
     * @return the view name for the edit blog form
     */
    @GetMapping("/admin/edit/{blogId}")
    public String showEditForm(@PathVariable String blogId,
                               Model model,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        if (isNotPermit(session)) {
            notificationService.addErrorMessage(redirectAttributes, "Access denied. Administrator privileges required.");
            return "redirect:/blogs";
        }

        try {
            // Retrieve blog by ID
            Optional<BlogResponse> blogOpt = blogService.getBlogById(blogId);
            if (blogOpt.isEmpty()) {
                notificationService.addErrorMessage(redirectAttributes, "Blog not found. It may have been deleted.");
                return "redirect:/blogs";
            }

            BlogResponse blog = blogOpt.get();

            // Create and populate BlogRequest with existing data
            BlogRequest blogRequest;
            if (!model.containsAttribute("blogRequest")) {
                blogRequest = BlogRequest.builder()
                        .title(blog.getTitle())
                        .content(blog.getContent())
                        .excerpt(blog.getExcerpt())
                        .categoryId(blog.getCategoryId())
                        .status(blog.getStatus())
                        .featured(blog.getFeatured())
                        .tagNames(blog.getTags())
                        .build();
                model.addAttribute("blogRequest", blogRequest);
            }

            // Prepare dropdown data for form
            prepareFormDropdownData(model);

            // Add additional attributes for display purposes
            model.addAttribute("blog", blog);
            model.addAttribute("pageTitle", "Edit Blog: " + blog.getTitle());

            return "blogs/edit";

        } catch (Exception e) {
            notificationService.addErrorMessage(redirectAttributes,
                    "Error loading blog for editing: " + e.getMessage());
            return "redirect:/blogs";
        }
    }

    /**
     * Processes the update of an existing blog with validation.
     * <p>
     * Handles form validation, HTML content processing, tag management,
     * and provides comprehensive feedback to users.
     * </p>
     *
     * @param blogId             the ID of the blog to update
     * @param request            the blog update request with form data
     * @param bindingResult      validation results from form binding
     * @param session            current HTTP session for authentication
     * @param redirectAttributes attributes for redirect scenarios
     * @return redirect to appropriate page based on operation result
     */
    @PostMapping("/admin/edit/{blogId}")
    public String updateBlog(@PathVariable String blogId,
                             @ModelAttribute("blogRequest") @Valid BlogRequest request,
                             BindingResult bindingResult,
                             HttpSession session,
                             @RequestParam(value = "thumbnail") MultipartFile file,
                             RedirectAttributes redirectAttributes) {
        if (isNotPermit(session)) {
            notificationService.addErrorMessage(redirectAttributes, "Access denied. Administrator privileges required.");
            return "redirect:/blogs";
        }

        // Handle validation errors
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.blogRequest", bindingResult);
            redirectAttributes.addFlashAttribute("blogRequest", request);
            notificationService.addErrorMessage(redirectAttributes,
                    "Please check the information entered. All fields must be valid.");
            return "redirect:/blogs/admin/edit/" + blogId;
        }

        try {
            // Perform the update operation
            if (file != null && !file.isEmpty()) {
                try {
                    String url = s3Service.uploadFile(file);
                    request.setThumbnailUrl(url);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            BlogResponse updatedBlog = blogService.updateBlog(blogId, request);

            // Success notification with blog title
            notificationService.addSuccessMessage(redirectAttributes,
                    "Blog post '" + request.getTitle() + "' has been successfully updated!");

            return "redirect:/blogs";

        } catch (AppException e) {
            // Handle business logic exceptions
            notificationService.addErrorMessage(redirectAttributes, e.getMessage());
            redirectAttributes.addFlashAttribute("blogRequest", request);
            return "redirect:/blogs/admin/edit/" + blogId;

        } catch (Exception e) {
            // Handle unexpected errors
            notificationService.addErrorMessage(redirectAttributes,
                    "An unexpected error occurred while updating the blog: " + e.getMessage());
            redirectAttributes.addFlashAttribute("blogRequest", request);
            return "redirect:/blogs/admin/edit/" + blogId;
        }
    }

    /**
     * Displays the blog details in read-only format with full HTML rendering.
     * <p>
     * Retrieves comprehensive blog information including formatted content,
     * tags, category information, and view statistics.
     * Increments view count for analytics purposes.
     * </p>
     *
     * @param blogId             the ID of the blog to view
     * @param model              model to pass attributes to the view
     * @param session            current HTTP session for authentication
     * @param redirectAttributes attributes for redirect scenarios
     * @return the view name for the blog details page
     */
    @GetMapping("/{blogId}")
    public String viewBlog(@PathVariable String blogId,
                           Model model,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        try {
            // Existing blog loading logic
            Optional<BlogResponse> blogOpt = blogService.getBlogById(blogId);
            if (blogOpt.isEmpty()) {
                notificationService.addErrorMessage(redirectAttributes, "Blog not found or has been removed.");
                return "redirect:/blogs";
            }

            BlogResponse blog = blogOpt.get();

            // View count logic (existing)
            Set<String> viewedBlogs = (Set<String>) session.getAttribute("viewedBlogs");
            if (viewedBlogs == null) {
                viewedBlogs = new HashSet<>();
            }
            if (!viewedBlogs.contains(blogId)) {
                blogService.incrementViewCount(blogId);
                viewedBlogs.add(blogId);
                session.setAttribute("viewedBlogs", viewedBlogs);
            }

            // ** THÊM PHẦN XỬ LÝ COMMENT **
            User currentUser = (User) session.getAttribute("user");
            String currentUserId = currentUser != null ? currentUser.getUserId() : null;
            // Load comments cho blog
            List<CommentResponse> comments = commentService.getCommentsByBlogId(blogId, currentUserId);
            long commentCount = commentService.getCommentCountByBlog(blogId);
            List<CommentResponse> popularComments = commentService.getPopularCommentsByBlog(blogId);

            // Existing formatting logic
            String authorName = userService.getUserName(blog.getAuthorId());
            blog.setAuthorName(authorName != null ? authorName : "Unknown User");
            String formattedTags = formatTags(blog.getTags());

            // Category logic (existing)
            String categoryName = "Uncategorized";
            String categoryId = "";
            if (blog.getCategoryId() != null) {
                try {
                    Optional<BlogCategoryResponse> categoryOpt = blogCategoryService.getCategoryById(blog.getCategoryId());
                    if (categoryOpt.isPresent()) {
                        categoryName = categoryOpt.get().getCategoryName();
                        categoryId = categoryOpt.get().getCategoryId().toString();
                    }
                } catch (Exception e) {
                    // Keep default category name
                }
            }

            List<BlogResponse> recentBlogs = blogService.getRecentBlogs(3);

            // Add all attributes including comments
            model.addAttribute("blog", blog);
            model.addAttribute("formattedTags", formattedTags);
            model.addAttribute("categoryName", categoryName);
            model.addAttribute("categoryId", categoryId);
            model.addAttribute("recentBlogs", recentBlogs);
            model.addAttribute("pageTitle", "Blog Details: " + blog.getTitle());

            // ** COMMENT ATTRIBUTES **
            model.addAttribute("comments", comments);
            model.addAttribute("commentCount", commentCount);
            model.addAttribute("popularComments", popularComments);
            model.addAttribute("isLoggedIn", currentUser != null);
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("commentRequest", new CommentRequest()); // For form binding

            return "blogs/view";

        } catch (Exception e) {
            notificationService.addErrorMessage(redirectAttributes,
                    "Error loading blog details: " + e.getMessage());
            return "redirect:/blogs";
        }
    }



    /**
     * Handles blog deletion with comprehensive security and validation.
     * <p>
     * Implements POST-Redirect-GET pattern to prevent duplicate submissions
     * and provides user feedback through flash messages.
     * </p>
     *
     * @param blogId             the unique identifier of the blog to delete
     * @param session            current HTTP session for authentication
     * @param redirectAttributes Spring MVC redirect attributes for flash messages
     * @return redirect view name with appropriate success or error feedback
     */
    @GetMapping("/admin/delete/{blogId}")
    public String deleteBlog(@PathVariable String blogId,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {

        if (isNotPermit(session)) {
            notificationService.addErrorMessage(redirectAttributes, "Access denied. Admin privileges required.");
            return "redirect:/blogs";
        }

        try {
            // Validate blog existence before attempting deletion
            Optional<BlogResponse> blogOpt = blogService.getBlogById(blogId);
            if (blogOpt.isEmpty()) {
                notificationService.addErrorMessage(redirectAttributes, "Blog not found.");
                return "redirect:/blogs";
            }

            // Store blog title for success message before deletion
            String blogTitle = blogOpt.get().getTitle();

            // Attempt to delete the blog through the service layer
            blogService.deleteBlog(blogId);

            // Deletion successful - add success notification with blog title
            notificationService.addSuccessMessage(redirectAttributes,
                    "Blog post '" + blogTitle + "' deleted successfully");

            return "redirect:/blogs";

        } catch (IllegalStateException e) {
            // Handle business rule violations
            notificationService.addErrorMessage(redirectAttributes, e.getMessage());
            return "redirect:/blogs/admin/delete/" + blogId;

        } catch (Exception e) {
            // Handle any unexpected errors during deletion process
            notificationService.addErrorMessage(redirectAttributes,
                    "Error deleting blog: " + e.getMessage());
            return "redirect:/blogs";
        }
    }

    /**
     * Publishes a draft blog post, making it visible to public.
     * <p>
     * Changes blog status from DRAFT to PUBLISHED and sets publication date.
     * Provides user feedback and redirects to blog list.
     * </p>
     */
    @PostMapping("/admin/{blogId}/publish")
    public String publishBlog(@PathVariable String blogId,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        if (isNotPermit(session)) {
            notificationService.addErrorMessage(redirectAttributes, "Access denied. Admin privileges required.");
            return "redirect:/blogs";
        }

        try {
            BlogResponse publishedBlog = blogService.publishBlog(blogId);
            notificationService.addSuccessMessage(redirectAttributes,
                    "Blog '" + publishedBlog.getTitle() + "' has been published successfully!");

        } catch (AppException e) {
            notificationService.addErrorMessage(redirectAttributes, e.getMessage());
        } catch (Exception e) {
            notificationService.addErrorMessage(redirectAttributes,
                    "Error publishing blog: " + e.getMessage());
        }

        return "redirect:/blogs";
    }

    /**
     * Unpublishes a blog post, changing status to DRAFT.
     * <p>
     * Makes the blog invisible to public while keeping it available for editing.
     * </p>
     */
    @PostMapping("/admin/{blogId}/unpublish")
    public String unpublishBlog(@PathVariable String blogId,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        if (isNotPermit(session)) {
            notificationService.addErrorMessage(redirectAttributes, "Access denied. Admin privileges required.");
            return "redirect:/blogs";
        }

        try {
            BlogResponse unpublishedBlog = blogService.unpublishBlog(blogId);
            notificationService.addSuccessMessage(redirectAttributes,
                    "Blog '" + unpublishedBlog.getTitle() + "' has been unpublished successfully!");

        } catch (AppException e) {
            notificationService.addErrorMessage(redirectAttributes, e.getMessage());
        } catch (Exception e) {
            notificationService.addErrorMessage(redirectAttributes,
                    "Error unpublishing blog: " + e.getMessage());
        }

        return "redirect:/blogs";
    }

    // ========== HELPER METHODS ==========

    /**
     * Validates and sanitizes the sort field to prevent SQL injection.
     */
    private String validateSortField(String sortBy) {
        if (sortBy != null && ALLOWED_SORT_FIELDS.contains(sortBy)) {
            return sortBy;
        }
        return "createdAt";
    }

    /**
     * Sanitizes and validates keyword input for search operations.
     */
    private String sanitizeKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return null;
        }

        String sanitized = keyword.trim().replaceAll("<[^>]*>", "");

        if (sanitized.length() > 200) {
            sanitized = sanitized.substring(0, 200);
        }
        return sanitized.isEmpty() ? null : sanitized;
    }

    /**
     * Validates category ID input.
     */
    private Integer validateCategoryId(Integer categoryId) {
        if (categoryId == null || categoryId <= 0) {
            return null;
        }
        return categoryId;
    }

    /**
     * Validates date input to ensure it's within reasonable range.
     */
    private LocalDate validateDate(LocalDate date) {
        if (date == null) {
            return null;
        }

        LocalDate now = LocalDate.now();
        LocalDate minDate = now.minusYears(10);
        LocalDate maxDate = now.plusYears(1);

        if (date.isBefore(minDate) || date.isAfter(maxDate)) {
            return null;
        }

        return date;
    }

    /**
     * Sanitizes and validates tag list input.
     */
    private List<String> sanitizeTags(List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return null;
        }

        List<String> sanitized = tags.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(tag -> !tag.isEmpty() && tag.length() <= 50)
                .distinct()
                .limit(20)
                .collect(Collectors.toList());

        return sanitized.isEmpty() ? null : sanitized;
    }

    /**
     * Sanitizes user ID input for filtering.
     */
    private String sanitizeUserId(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            return null;
        }

        String trimmed = userId.trim();

        if (trimmed.length() == 36 && trimmed.matches("[a-fA-F0-9-]+")) {
            return trimmed;
        }

        return null;
    }

    /**
     * Prepares filter-related attributes for dropdown UI components.
     */
    private void prepareFilterAttributes(Model model, BlogFilterParams filterParams) {
        try {
            // Get available categories for filter dropdown
            List<BlogCategoryResponse> availableCategories = blogCategoryService.getAllCategories();
            // Trong controller
            model.addAttribute("availableCategories",
                    availableCategories != null ? availableCategories : Collections.emptyList());

            // Get available tags for filter dropdown
            List<BlogTagResponse> availableTags = blogTagService.getAllTags();
            model.addAttribute("availableTags", availableTags);

            // Get available authors for filter dropdown
            Map<String, String> availableAuthors = userService.getAllAuthorsMap();
            model.addAttribute("availableAuthors", availableAuthors);

            // Get available blog statuses
            model.addAttribute("availableStatuses", BlogStatus.values());

            // Build active filters list for display
            if (filterParams.hasActiveFilters()) {
                List<String> activeFilters = buildActiveFiltersList(filterParams);
                model.addAttribute("activeFilters", activeFilters);
                model.addAttribute("activeFilterCount", activeFilters.size());
            }

        } catch (Exception e) {
            System.err.println("Error preparing filter attributes: " + e.getMessage());
        }
    }

    /**
     * Prepares filter button states for active indication in UI.
     */
    private void prepareFilterButtonStates(Model model, BlogFilterParams filterParams) {
        Map<String, Boolean> filterStates = new HashMap<>();

        filterStates.put("statusActive", filterParams.getStatus() != null);
        filterStates.put("categoryActive", filterParams.getCategoryId() != null);
        filterStates.put("dateActive",
                filterParams.getFromDate() != null || filterParams.getToDate() != null);
        filterStates.put("tagsActive",
                filterParams.getSelectedTags() != null && !filterParams.getSelectedTags().isEmpty());
        filterStates.put("authorActive",
                filterParams.getAuthorId() != null && !filterParams.getAuthorId().trim().isEmpty());
        filterStates.put("featuredActive", filterParams.getFeatured() != null);

        model.addAttribute("filterStates", filterStates);
    }

    /**
     * Builds a list of active filter descriptions for user display.
     */
    private List<String> buildActiveFiltersList(BlogFilterParams filterParams) {
        List<String> activeFilters = new ArrayList<>();

        if (filterParams.getKeyword() != null && !filterParams.getKeyword().trim().isEmpty()) {
            activeFilters.add("Search: " + filterParams.getKeyword());
        }

        if (filterParams.getStatus() != null) {
            activeFilters.add("Status: " + filterParams.getStatus().getDisplayName());
        }

        if (filterParams.getCategoryId() != null) {
            try {
                Optional<BlogCategoryResponse> categoryOpt = blogCategoryService.getCategoryById(filterParams.getCategoryId());
                if (categoryOpt.isPresent()) {
                    activeFilters.add("Category: " + categoryOpt.get().getCategoryName());
                } else {
                    activeFilters.add("Category: Selected");
                }
            } catch (Exception e) {
                activeFilters.add("Category: Selected");
            }
        }

        if (filterParams.getFromDate() != null || filterParams.getToDate() != null) {
            StringBuilder dateRange = new StringBuilder("Date: ");
            if (filterParams.getFromDate() != null) {
                dateRange.append(filterParams.getFromDate());
            }
            dateRange.append(" - ");
            if (filterParams.getToDate() != null) {
                dateRange.append(filterParams.getToDate());
            }
            activeFilters.add(dateRange.toString());
        }

        if (filterParams.getSelectedTags() != null && !filterParams.getSelectedTags().isEmpty()) {
            activeFilters.add("Tags: " + String.join(", ", filterParams.getSelectedTags()));
        }

        if (filterParams.getAuthorId() != null && !filterParams.getAuthorId().trim().isEmpty()) {
            try {
                String authorName = userService.getUserName(filterParams.getAuthorId());
                activeFilters.add("Author: " + (authorName != null ? authorName : "Unknown"));
            } catch (Exception e) {
                activeFilters.add("Author: Selected");
            }
        }

        if (filterParams.getFeatured() != null && filterParams.getFeatured()) {
            activeFilters.add("Featured: Yes");
        }

        return activeFilters;
    }

    /**
     * Prepares dropdown data needed for add/edit forms.
     */
    private void prepareFormDropdownData(Model model) {
        try {
            // Add available categories for form
            List<BlogCategoryResponse> availableCategories = blogCategoryService.getAllCategories();
            model.addAttribute("availableCategories", availableCategories);
            // Add available tags for form
            List<BlogTagResponse> availableTags = blogTagService.getAllTags();
            model.addAttribute("availableTags", availableTags);

            // Add blog statuses for form
            model.addAttribute("availableStatuses", BlogStatus.values());

        } catch (Exception e) {
            System.err.println("Error preparing form dropdown data: " + e.getMessage());
        }
    }

    /**
     * Adds blog statistics for dashboard insights.
     */
    private void addBlogStatistics(Model model, Page<BlogResponse> blogs) {
        try {
            Map<String, Object> statistics = new HashMap<>();

            statistics.put("totalBlogs", blogs.getTotalElements());
            statistics.put("currentPageBlogs", blogs.getNumberOfElements());
            statistics.put("totalPages", blogs.getTotalPages());
            statistics.put("currentPage", blogs.getNumber() + 1);

            // Calculate status distribution from current page
            Map<BlogStatus, Long> statusCount = blogs.getContent().stream()
                    .collect(Collectors.groupingBy(BlogResponse::getStatus, Collectors.counting()));
            statistics.put("statusDistribution", statusCount);

            // Calculate total view count from current page
            int totalViews = blogs.getContent().stream()
                    .mapToInt(BlogResponse::getViewCount)
                    .sum();
            statistics.put("totalViews", totalViews);

            model.addAttribute("blogStatistics", statistics);

        } catch (Exception e) {
            System.err.println("Error calculating blog statistics: " + e.getMessage());
        }
    }

    /**
     * Formats tags list for consistent display across the application.
     */
    private String formatTags(List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return "No tags";
        }
        return String.join(", ", tags);
    }

    /**
     * Checks if current user has permission to access admin functions.
     */
    private boolean isNotPermit(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user == null || user.getRole() != Role.admin;
    }
    @PostMapping("/api/upload/thumbnail")
    public ResponseEntity<String> uploadThumbnail(
            @RequestParam("file") MultipartFile file) throws IOException {

        // reuse the same S3Service used for avatar
        String url = s3Service.uploadFile(file);      // returns public URL
        return ResponseEntity.ok(url);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmer);
    }
}

