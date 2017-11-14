
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class HttpRequestHandler implements Runnable {

  public static void runServer() {
    int port = 8080;
    ServerSocket server_socket = null;
    try {

      server_socket = new ServerSocket(port);
      System.out.println("httpServer running on port " + server_socket.getLocalPort());

      // server infinite loop
      while (true) {
        Socket socket = server_socket.accept();
        System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());

        // Construct handler to process the HTTP request message.
        try {
          HttpRequestHandler request = new HttpRequestHandler(socket);
          // Create a new thread to process the request.
          Thread thread = new Thread(request);

          // Start the thread.
          thread.start();
        } catch (Exception e) {
          System.out.println(e);
        }
      }
    } catch (IOException e) {
      System.out.println(e);
    }

  }

  final static String CRLF = "\r\n";

  final static String HelloWorld = "Hello World!";

  Socket socket;

  InputStream input;

  OutputStream output;

  BufferedReader br;

  public HttpRequestHandler(Socket socket) throws Exception {
    this.socket = socket;
    this.input = socket.getInputStream();
    this.output = socket.getOutputStream();
    this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  }

  public void run() {
    try {
      processRequest();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void processRequest() throws Exception {
    while (true) {

      String headerLine = br.readLine();
      System.out.println(headerLine);
      if (headerLine.equals(CRLF) || headerLine.equals(""))
        break;

      StringTokenizer s = new StringTokenizer(headerLine);
      String temp = s.nextToken();

      if (temp.equals("GET")) {

        String serverLine = "Server: Simple Java Http Server";
        String statusLine = null;
        String contentTypeLine = null;
        String entityBody = null;
        String contentLengthLine = "error";
        statusLine = "HTTP/1.0 200 OK" + CRLF;
        contentTypeLine = "Content-type: text/html" + CRLF;
        contentLengthLine = "Content-Length: " + HelloWorld.length() + CRLF;

        // Send the status line.
        output.write(statusLine.getBytes());
        System.out.println(statusLine);

        // Send the server line.
        output.write(serverLine.getBytes());
        System.out.println(serverLine);

        // Send the content type line.
        output.write(contentTypeLine.getBytes());
        System.out.println(contentTypeLine);

        // Send the Content-Length
        output.write(contentLengthLine.getBytes());
        System.out.println(contentLengthLine);

        // Send a blank line to indicate the end of the header lines.
        output.write(CRLF.getBytes());
        System.out.println(CRLF);

        // Send the entity body.
        output.write(HelloWorld.getBytes());
      }
    }

    try {
      output.close();
      br.close();
      socket.close();
    } catch (Exception e) {
    }
  }
}
