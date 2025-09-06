# Blog Backend API

This is a Spring Boot Kotlin application for a blog backend system.

## API Base URL Configuration

The API is configured with a base URL prefix for all endpoints. All API endpoints are accessible under the `/api/v1` path.

### Configuration

The API base URL is configured in `src/main/resources/application.properties`:

```properties
# API Base URL Configuration
# This sets a base path for all API endpoints
server.servlet.context-path=/api/v1
```

### API Endpoints

All endpoints are now prefixed with `/api/v1`:

#### Authors
- `POST /api/v1/authors` - Create a new author
- `GET /api/v1/authors/{authorId}/posts` - Get posts by author (with pagination)
- `POST /api/v1/authors/{authorId}/posts` - Create a new post for an author

#### Posts
- `PUT /api/v1/posts/{postId}` - Update a post
- `DELETE /api/v1/posts/{postId}` - Delete a post
- `GET /api/v1/posts/search` - Search posts by title

### Example Usage

```bash
# Create an author
curl -X POST "http://localhost:8080/api/v1/authors" \
  -H "Content-Type: application/json" \
  -d '{"name": "John Doe"}'

# Create a post
curl -X POST "http://localhost:8080/api/v1/authors/1/posts" \
  -H "Content-Type: application/json" \
  -d '{"title": "My First Post", "content": "This is the content", "category": "Technology"}'

# Get posts by author
curl "http://localhost:8080/api/v1/authors/1/posts?page=0&size=10"
```

## Running the Application

```bash
gradle bootRun
```

The application will start on port 8080 with the context path `/api/v1`.

## Configuration Details

The `application.properties` file includes:
- **API Base URL**: `/api/v1` prefix for all endpoints
- **Server Port**: 8080
- **Database**: H2 in-memory database for development
- **H2 Console**: Available at `/api/v1/h2-console`
- **Logging**: Debug level for web and application packages