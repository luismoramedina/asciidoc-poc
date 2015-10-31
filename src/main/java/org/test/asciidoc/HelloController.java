package org.test.asciidoc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@Controller
public class HelloController {

    @RequestMapping({"/"})
    public ModelAndView handleRequest(HttpServletRequest request)
            throws Exception {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document parse = documentBuilder.parse(TestService.class.getResourceAsStream("/my-test.xml"));
        NodeList screen = parse.getElementsByTagName("screen");
        String data = null;
        for (int i = 0; i < screen.getLength(); i++) {
            Node thisScreen = screen.item(i);
            data = thisScreen.getFirstChild().getNodeValue();
        }
        ModelAndView model = new ModelAndView("model");
        model.addObject("data", data);
        model.setViewName("test");
        return model;
    }

}
