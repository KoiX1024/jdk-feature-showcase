package io.example.jdk15;

public class TextBlocksDemo {
    public static void main(String[] args){
        // Java 8 风格
        String htmlOld = "<html>\n" +
                "    <body>\n" +
                "        <p>Hello, World</p>\n" +
                "    </body>\n" +
                "</html>";

        // Java 15+ Text Blocks
        String htmlNew = """
                   <html>
                       <body>
                           <p>Hello, World</p>
                       </body>
                   </html>
                   """; // 注意结束的三个引号在新的一行，或者与最后一行内容同行

        String query = """
                 SELECT "EMP_ID", "LAST_NAME" FROM "EMPLOYEE_TB"
                 WHERE "CITY" = 'INDIANAPOLIS'
                 ORDER BY "EMP_ID", "LAST_NAME";
                 """;
        System.out.println(htmlOld);
        System.out.println(htmlNew);
        System.out.println(query);
    }
}
