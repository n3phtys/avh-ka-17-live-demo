package huette.gruin


val htmlForMessenger: String = "<!DOCTYPE html>\n" +
        "<html lang=\"de\">\n" +
        "\n" +
        "<head>\n" +
        "    <meta charset=\"utf-8\">\n" +
        "    <title>Unser erstes HTML Dokument</title>\n" +
        "    <!--link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\" integrity=\"sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb\" crossorigin=\"anonymous\"-->\n" +
        "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">" +
        "  <script src=\"http://code.jquery.com/jquery-1.9.1.js\"></script>\n" +
        "</head>\n" +
        "\n" +
        "<body>\n" +
        "    <!-- page content -->\n" +
        " <div class=\"container\"> \n" +
        "    <h1>Hüttenchat</h1>\n" +
        "    \n" +
        "\n" +
        "\n" +
        "\n" +
        "    <script>\n" +
        "        function refreshViaAjax() {\n" +
        "            var xmlhttp = new XMLHttpRequest();\n" +
        "            var url = \"/messages\";\n" +
        "\n" +
        "            xmlhttp.onreadystatechange = function () {\n" +
        "                if (this.readyState == 4 && this.status == 200) {\n" +
        "                    var myArr = JSON.parse(this.responseText);\n" +
        "                    var html = \"<tr><th>Zeitstempel</th><th>Nachricht</th><th>Absender</th></tr>\";\n" +
        "                \n" +
        "                    for (var i = 0; i < myArr.length; i++) {\n" +
        "                        html += \"<tr>\";\n" +
        "                        html += \"<td>\";\n" +
        "                        html += myArr[i].timestamp;\n" +
        "                        html += \"</td>\";\n" +
        "                        html += \"<td>\";\n" +
        "                        html += myArr[i].message;\n" +
        "                        html += \"</td>\";\n" +
        "                        html += \"<td>\";\n" +
        "                        html += myArr[i].hostname;\n" +
        "                        html += \"</td>\";\n" +
        "                        html += \"</tr>\";\n" +
        "                    }\n" +
        "                    \n" +
        "                    document.getElementById(\"my_table\").innerHTML = html;\n" +
        "                }\n" +
        "            };\n" +
        "            xmlhttp.open(\"GET\", url, true);\n" +
        "            xmlhttp.send();\n" +
        "\n" +
        "\n" +
        "        }\n" +
        "\n" +
        "\n" +
        "        setInterval(function () {\n" +
        "            refreshViaAjax();\n" +
        "        }, 1000);\n" +
        " window.onload = refreshViaAjax;" +
        "    </script>\n" +
        "\n" +
        "\n" +
        "    <form id=\"my_form\" action=\"/messages\" class=\"\">\n" +
        " <div class=\"form-group\">\n" +
        "    <label for=\"email\">Nachricht:</label>\n" +
        "    <input type=\"text\" class=\"form-control\" id=\"message\" name=\"message\" value=\"\"\n" +
        "    required\n" +
        "    placeholder=\"Deine Nachricht für den Chat\" >\n" +
        "  </div> \n" +
        "  <input type=\"submit\" class=\"btn btn-default\" value=\"Senden\">\n" +
        "</form> \n" +
        "\n" +
        "<script type='text/javascript'>\n" +
        "    /* attach a submit handler to the form */\n" +
        "    \$(\"#my_form\").submit(function(event) {\n" +
        "\n" +
        "      /* stop form from submitting normally */\n" +
        "      event.preventDefault();\n" +
        "\n" +
        "      /* get the action attribute from the <form action=\"\"> element */\n" +
        "      var $" + "form = \$( this ),\n" +
        "          url = $" + "form.attr( 'action' );\n" +
        "\n" +
        "      /* Send the data using post with element id name and name2*/\n" +
        "      var posting = \$.post( url, { message: \$('#message').val() } );\n" +
        "\n" +
        "document.getElementById('message').value = '';" +
        "\n" +
        "      /* Alerts the results */\n" +
        "      posting.done(function( data ) {\n" +
        "       refreshViaAjax(); \n" +
        "      });\n" +
        "    });\n" +
        "</script>\n" +

        "  <div class=\"table-responsive\">" +
        "  <table id=\"my_table\" class=\"table\"></table>\n" +
        "\n" +
        "</div></div>\n" +
        "</body>\n" +
        "\n" +
        "</html>" //TODO: implement


val endpoint = "/messages"

