/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.84
 * Generated at: 2022-12-23 01:18:41 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views._02;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.File;

public final class imageForm_005fajax_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/D:/A_TeachingMaterial/06.JSP_Spring/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/WebStudy01/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1671598935706L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.io.File");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write(" <!-- 코어태그 추가 -->\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<!-- jpeg-red, png-green, gif-blue -->\r\n");
      out.write("<style>\r\n");
      out.write(".red {\r\n");
      out.write("   background-color : red;\r\n");
      out.write("}\r\n");
      out.write(".green {\r\n");
      out.write("   background-color : green;\r\n");
      out.write("}\r\n");
      out.write(".blue {\r\n");
      out.write("   background-color : blue;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/js/jquery-3.6.1.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("   <form name=\"imgForm\" action=\"");
      out.print(request.getContextPath());
      out.write("/imageStreaming.do\"><!-- web.xml에 있는 파일명이 틀렸다. -->\r\n");
      out.write("      <select name=\"image\">\r\n");
      out.write("      </select>\r\n");
      out.write("      <input type=\"submit\" value=\"전송\">\r\n");
      out.write("   </form>\r\n");
      out.write("   <div id=\"imgArea\">\r\n");
      out.write("   	\r\n");
      out.write("   		<!-- 마지막에 본 이미지가 복원되어있어야하는 부분 , 선택했던 것 -->\r\n");
      out.write("		\r\n");
      out.write("   </div>\r\n");
      out.write("<script src=\"");
      out.print( request.getContextPath() );
      out.write("/resources/js/jquery-3.6.1.min.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("   const DIVTAG = $(\"#imgArea\");\r\n");
      out.write("   const SELECTTAG = $(\"[name=image]\").on(\"change\", function(event){\r\n");
      out.write("      let option = $(this).find(\"option:selected\");\r\n");
      out.write("      let mime = option.attr(\"class\");\r\n");
      out.write("      let clzName = matchedClass(mime);\r\n");
      out.write("      $(this).removeClass(colors);\r\n");
      out.write("      $(this).addClass(clzName);\r\n");
      out.write("      \r\n");
      out.write("      let srcURL = document.imgForm.action;\r\n");
      out.write("      let queryString = $(document.imgForm).serialize();\r\n");
      out.write("      let src = \"%U?%P\".replace(\"%U\", srcURL).replace(\"%P\", queryString);\r\n");
      out.write("      \r\n");
      out.write("      let img = $(\"<img>\").attr(\"src\", src)\r\n");
      out.write("      DIVTAG.html(img);\r\n");
      out.write("//    }).on(\"change\", function(event){\r\n");
      out.write("//       event.target===this\r\n");
      out.write("\r\n");
      out.write("   });\r\n");
      out.write("   const changeCondition = {\r\n");
      out.write("         jpeg : \"red\",\r\n");
      out.write("         png : \"green\",\r\n");
      out.write("         gif : \"blue\"\r\n");
      out.write("   }\r\n");
      out.write("   const colors = [];\r\n");
      out.write("   $.each(changeCondition, function(prop, propValue){\r\n");
      out.write("      colors.push(propValue);\r\n");
      out.write("   });\r\n");
      out.write("   let matchedClass = function(mime){ // 자바스크립트에서는 함수도 객체이다.\r\n");
      out.write("      let clzName = \"\";\r\n");
      out.write("      for (let prop in changeCondition) {\r\n");
      out.write("         let idx = mime.indexOf(prop);\r\n");
      out.write("         if (idx >= 0) {\r\n");
      out.write("            clzName = changeCondition[prop];\r\n");
      out.write("            break;\r\n");
      out.write("         }\r\n");
      out.write("      }\r\n");
      out.write("      return clzName;\r\n");
      out.write("   }\r\n");
      out.write("   $.ajax({\r\n");
      out.write("      dataType : \"json\", // request-header 설정 ! accept하는 부분이 달라짐 / request-header와 response-header의 accept 하는 부분이 같아야 함\r\n");
      out.write("      success : function(resp) { // resp 이미 unmarshalling이 끝난 시점의 javascript 객체임\r\n");
      out.write("         // for (let idx in resp) {} \r\n");
      out.write("         // $.each(resp, function(index, element){console.log(index + element + this);});\r\n");
      out.write("         /*\r\n");
      out.write("         var txt = \"\";\r\n");
      out.write("         for(var i = 0; i < resp.length; i++){ \r\n");
      out.write("            txt += \"<option>\" + resp[i].name + \"</option>\";\r\n");
      out.write("         }\r\n");
      out.write("         $('select').html(txt);\r\n");
      out.write("         */\r\n");
      out.write("         let options = [];\r\n");
      out.write("         $.each(resp, function(index, file){ // 파일의 이름은 file이나 this가 갖고 있음\r\n");
      out.write("            let option = $('<option>')\r\n");
      out.write("               .addClass(file.mime)\r\n");
      out.write("               .html(file.name); // $('<??>') 새로운 태그를 만들겠다! 근데 아직 객체 상태\r\n");
      out.write("            options.push(option)\r\n");
      out.write("         });\r\n");
      out.write("         SELECTTAG.append(options);\r\n");
      out.write("         //쿠키 추가 --여기까지 끝났다고 하면 옵션이 만들어져있는 상태임\r\n");
      out.write("//          ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cookie['imageCookie']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(" // 이 해당쿠키를 가져올 수 있음\r\n");
      out.write("		 ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("      },\r\n");
      out.write("      error : function(jqXHR, status, error) {\r\n");
      out.write("         console.log(jqXHR);\r\n");
      out.write("         console.log(status);\r\n");
      out.write("         console.log(error);\r\n");
      out.write("      }\r\n");
      out.write("   });\r\n");
      out.write("//    $('select').on('change',function(){\r\n");
      out.write("//       console.log($('select option:selected').attr('class'));\r\n");
      out.write("//       if ($('select option:selected').attr('class') == \"image/jpeg\"){\r\n");
      out.write("//          $(this).attr(\"class\", \"image/jpeg red\");\r\n");
      out.write("//       } else if ($('select option:selected').attr('class') == \"image/png\"){\r\n");
      out.write("//          $(this).attr(\"class\", \"image/png green\");\r\n");
      out.write("//       } else if ($('select option:selected').attr('class') == \"image/gif\"){\r\n");
      out.write("//          $(this).attr(\"class\", \"image/gif blue\");\r\n");
      out.write("//       } else {\r\n");
      out.write("         \r\n");
      out.write("//       }\r\n");
      out.write("//    });\r\n");
      out.write("   \r\n");
      out.write("//    let obj = { jpeg : \"red\" }\r\n");
      out.write("//    obj.png = \"green\"\r\n");
      out.write("//    obj['gif']=\"blue\";\r\n");
      out.write("//    obj -> {jpeg: 'red', png: 'green', gif: 'blue'}\r\n");
      out.write("//    let prop = \"gif\";\r\n");
      out.write("//    obj[prop] -> 'blue'\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    boolean _jspx_th_c_005fif_005f0_reused = false;
    try {
      _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f0.setParent(null);
      // /WEB-INF/views/02/imageForm_ajax.jsp(97,3) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty cookie['imageCookie']}", boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null)).booleanValue());
      int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
      if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("	         SELECTTAG.val(\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cookie['imageCookie']['value']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
          out.write("\");\r\n");
          out.write("	         SELECTTAG.trigger('change');\r\n");
          out.write("		 ");
          int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      _jspx_th_c_005fif_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fif_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fif_005f0_reused);
    }
    return false;
  }
}
