package huette.gruin


val htmlForMessenger: String = "<!DOCTYPE html>\n" +
        "<html lang=\"de\">\n" +
        "\n" +
        "<head>\n" +
        "    <meta charset=\"utf-8\">\n" +
        "    <title>Unser erstes HTML Dokument</title>\n" +
        "    <link rel=\"stylesheet\" href=\"style.css\">\n" +
        "  <script src=\"http://code.jquery.com/jquery-1.9.1.js\"></script>\n" +
        "</head>\n" +
        "\n" +
        "<body>\n" +
        "    <!-- page content -->\n" +
        "\n" +
        "    <h1>Hütten Chat</h1>\n" +
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
        "            console.log(\"A tick happens\");\n" +
        "            refreshViaAjax();\n" +
        "        }, 2000);\n" +
        "    </script>\n" +
        "\n" +
        "\n" +
        "    <form id=\"my_form\" action=\"/messages\">\n" +
        "  Nachricht:<br>\n" +
        "  <input type=\"text\" required id=\"message\" name=\"message\" value=\"\" placeholder=\"Deine Nachricht für den Chat\">\n" +
        "  <br>\n" +
        "  <input type=\"submit\" value=\"Absender\">\n" +
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
        "      var $"+"form = \$( this ),\n" +
        "          url = $"+"form.attr( 'action' );\n" +
        "\n" +
        "      /* Send the data using post with element id name and name2*/\n" +
        "      var posting = \$.post( url, { message: \$('#message').val() } );\n" +
        "\n" +
        "document.getElementById('message').value = '';" +
        "\n" +
        "      /* Alerts the results */\n" +
        "      posting.done(function( data ) {\n" +
        "        \n" +
        "      });\n" +
        "    });\n" +
        "</script>\n" +

        "    <table id=\"my_table\"></table>\n" +
        "\n" +
        "\n" +
        "</body>\n" +
        "\n" +
        "</html>" //TODO: implement




val ajaxScript: String = "" //TODO: implement querying server every X seconds

val ajaxEmptyTableTag: String = "" //TODO: implement 

val ajaxCombination: String = "$ajaxEmptyTableTag <script>$ajaxScript</script>"

val ajaxSendFormular: String = "" //TODO: implement

val jqueryInclude: String = "<script\n" +
        "  src=\"https://code.jquery.com/jquery-3.2.1.js\"\n" +
        "  integrity=\"sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE=\"\n" +
        "  crossorigin=\"anonymous\"></script>\n"

val ajaxForFormJS: String = "jQuery(document).ready(function(\$)\n" +
        "{\n" +
        "\t\$(\"#my_form\").submit(function(event) {\n" +
        "\t\t// Standard-Aktion abbrechen\n" +
        "\t\tevent.preventDefault();\t\n" +
        "\n" +
        "\t\t// Formular per AJAX senden\n" +
        "\t\tvar form=\$(this);\n" +
        "\t\t\$.ajax({\n" +
        "\t\t\ttype: 'POST',\n" +
        "\t\t\turl: form.prop('action'),\n" +
        "\t\t\tdata : form.serialize(),\n" +
        "\t\t\tdataType: 'json',\n" +
        "\t\t\tencode: true\n" +
        "\t\t}).done(function(data) {\n" +
        "\t\t\t// Aktionen bei Erfolg\n" +
        "\t\t\tconsole.log('done: '+data);\n" +
        "\t\t}).fail(function(data) {\n" +
        "\t\t\t// Aktionen bei einem Fehler\n" +
        "\t\t\tconsole.log('fail: '+data);\t\t\t\n" +
        "\t\t});\n" +
        "\t});\n" +
        "});"

val endpoint = "/messages"

