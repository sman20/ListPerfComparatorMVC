package sv.spring.mvc.listperfcomp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import sv.spring.mvc.listperfcomp.dao.TestCfgDAO;
import sv.spring.mvc.listperfcomp.dao.TestResultsDAO;
import sv.spring.mvc.listperfcomp.models.TestCfg;

@Controller
public class StartController {

    private final TestCfgDAO testCfgDAO;
    private TestResultsDAO testResultsDAO;

    @Autowired
    public StartController(TestCfgDAO testCfgDAO) {
        this.testCfgDAO = testCfgDAO;
        this.testResultsDAO = new TestResultsDAO();
    }

    @GetMapping("/start")
    public String start(Model model) {
        model.addAttribute("testcfg", testCfgDAO.getTestCfg());
//        System.out.println("@GetMapping(\"/start\")");
//        testCfgDAO.printCfg();
        return "start";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("testcfg", testCfgDAO.getTestCfg());
//        System.out.println("@GetMapping(\"/edit\")");
//        testCfgDAO.printCfg();
        return "edit";
    }

    @PatchMapping("/start")
    public String update(@ModelAttribute("testcfg") TestCfg newTestCfg) {
        testCfgDAO.setTestCfg(newTestCfg);
//        System.out.println("@PatchMapping(\"/start\")");
//        testCfgDAO.printCfg();
        return "start";
    }

    @GetMapping("/results")
    public String showResults(Model model) {
        // TODO calculate performance and pass the results over
        model.addAttribute("testcfg", testCfgDAO.getTestCfg());
//        System.out.println("@GetMapping(\"/results\")");
//        testCfgDAO.printCfg();
//        testCfgDAO.printLists();
        testCfgDAO.resetLists();
//        testCfgDAO.printLists();
        model.addAttribute("testresults", testResultsDAO.getTestResults(testCfgDAO));
//        testResultsDAO.printResults();
        return "results";
    }
}
