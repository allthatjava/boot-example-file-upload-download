This is example code for Spring File Upload and Download.
- Java:17
- Spring Boot: 3.2.2

### End Point
- POST `/file/uploadFile`: to upload file

- GET `/file/downloadFile/{fileName}`: to download file. full Uri will be provided within Upload response.

### Temp file
temporary uploadable file is in `/src/main/resources/files`