using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System.IO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using Excel = Microsoft.Office.Interop.Excel;
using System.Runtime.InteropServices;
using System.Collections.ObjectModel;

namespace VietnamWork_SearchSytem_Testing
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public class Testcase {
        public string[] loc_list;
        public string selectRecommend, inputJob;
        public int clickSearch;
        public string finalURL;
        public int checkMax3;
        public string testcaseName;
        public string failURL = "";
        public int isPass; // 0: no test, 1: pass, 2: failed
        public Testcase(string l1, string l2, string l3, string l4, string sR, string iJ, int cS, string fU, int cM3, string tN) {
            getLocList(l1, l2, l3, l4);
            selectRecommend = sR;
            inputJob = iJ;
            clickSearch = cS;
            finalURL = fU;
            checkMax3 = cM3;
            testcaseName = tN;
            isPass = 0;
        }
        void getLocList(string l1, string l2, string l3, string l4) {
            int count = 0;
            if (l1 != "Null") count++;
            if (l2 != "Null") count++;
            if (l3 != "Null") count++;
            if (l4 != "Null") count++;

            loc_list = new string[count];
            int i = 0;
            if (l1 != "Null") loc_list[i++] = l1;
            if (l2 != "Null") loc_list[i++] = l2;
            if (l3 != "Null") loc_list[i++] = l3;
            if (l4 != "Null") loc_list[i++] = l4;
        }
        public void printInfor() {
            Console.Write("Testcase: ");
            Console.Write(testcaseName);
            Console.Write(", State: ");
            switch (isPass) {
                case 0:
                    Console.Write("ISN'T TESTED");
                    break;
                case 1:
                    Console.Write("PASS");
                    break;
                case 2:
                    Console.Write("FAIL");
                    if (failURL != "") {
                        Console.Write('\n');
                        Console.WriteLine(finalURL);
                        Console.Write(failURL);
                    }
                    break;
                default:
                    Console.Write("ERROR VALUE");
                    break;
            }
            Console.Write('\n');
        }
    }
    public partial class MainWindow : Window
    {
        // record excel file
        // attribute
        WebDriver driver;
        // xpath
        string web_url = "";
        // step 1: location select
        string location_xpath = "", select_loc_xpath = "", warning_xpath = "", select_item = "", un_checked = "", item_title = "";
        // step 2: recommend job
        string recommend_job_xpath = "", icon_find_xpath = "", tag_keyword = "";
        // step 3: job input field
        string job_field_xpath = "";
        // step 4: search btn
        string find_job_xpath = "";
        // pop up
        string pop_up_close_xpath = "";
        // testcase
        Testcase[] testcase_list;
        public MainWindow() { InitializeComponent(); }
        // support method
        void sleep(float second) {
            Thread.Sleep((int)(second * 1000));
            try {
                var pop_up_close = driver.FindElement(By.XPath(pop_up_close_xpath));
                pop_up_close.Click();
            }
            catch {}
        }
        void clickSomething(By path) {
            try {
                var button = driver.FindElement(path);
                button.Click();
            }
            catch {}
        }
        // step 1: get location
        int uncheckAllLocation() {
            var select_loc = driver.FindElement(By.XPath(select_loc_xpath));
            int count = 0;
            foreach (var item in select_loc.FindElements(By.ClassName(select_item))) {
                sleep(0);
                var button = item.FindElement(By.TagName("span"));
                try { item.FindElement(By.ClassName(un_checked)); }
                catch (NoSuchElementException a) {
                    button.Click();
                    count++;
                }
            }
            return count;
        }
        void checkLocation(string[] loc_list) {
            clickSomething(By.XPath(location_xpath));
            uncheckAllLocation();

            foreach (var location in loc_list) {
                bool have_checked = false;
                sleep(0);
                var select_loc = driver.FindElement(By.XPath(select_loc_xpath));
                foreach (var item in select_loc.FindElements(By.ClassName(select_item))) {
                    sleep(0);
                    var title = item.FindElement(By.ClassName(item_title));

                    if (title.Text == location) {
                        try {
                            var button = item.FindElement(By.ClassName(un_checked));
                            button.Click();
                            have_checked = true;
                        }
                        catch { Console.WriteLine("Fail check"); }
                    }

                    if (have_checked == true) break;
                }
            }
        }
        void getLocationn(string[] loc_list) {
            // open location
            clickSomething(By.XPath(location_xpath));
            // unchecked all
            uncheckAllLocation();
            // checked location
            if (loc_list == null) return;
            checkLocation(loc_list);
        }
        // step 2: select recommend
        void selectJob(string job) {
            clickSomething(By.XPath(icon_find_xpath));
            sleep(0.2f);

            var zone = driver.FindElement(By.XPath(recommend_job_xpath));
            foreach (var item in zone.FindElements(By.TagName("a"))) {
                var keyword = item.FindElement(By.ClassName(tag_keyword));
                if (keyword.Text == job) {
                    item.Click();
                    return;
                }
            }
        }
        // step 3: input job
        void inputJob(string job) {
            var job_search = driver.FindElement(By.XPath(job_field_xpath));
            job_search.Clear();
            job_search.SendKeys(job);
        }
        // step 4: click search
        void clickSearch() {
            clickSomething(By.XPath(find_job_xpath));
        }
        // step 5: check web url
        bool checkWebUrl(string url) {
            if (driver.Url == url) return true;
            else {
                Console.WriteLine(driver.Url);
                Console.WriteLine(url);
                return false;
            }
        }
        // step 6: check max 3
        bool checkWarningPlace() {
            try {
                var warning = driver.FindElement(By.XPath(warning_xpath));
                int num_choice = uncheckAllLocation();
                if (num_choice != 3) {
                    Console.WriteLine("Not 3");
                    return false;
                }
                return true;
            }
            catch {}
            return false;
        }
        void getEnvi(Excel.Range xlRange) {
            // xpath
            web_url = xlRange.Cells[1, 2].Value2.ToString();
            // step 1: location select
            location_xpath = xlRange.Cells[2, 2].Value2.ToString();
            select_loc_xpath = xlRange.Cells[3, 2].Value2.ToString();
            warning_xpath = xlRange.Cells[4, 2].Value2.ToString();
            select_item = xlRange.Cells[5, 2].Value2.ToString();
            un_checked = xlRange.Cells[6, 2].Value2.ToString();
            item_title = xlRange.Cells[7, 2].Value2.ToString();
            // step 2: recommend job
            recommend_job_xpath = xlRange.Cells[8, 2].Value2.ToString();
            icon_find_xpath = xlRange.Cells[9, 2].Value2.ToString();
            tag_keyword = xlRange.Cells[10, 2].Value2.ToString();
            // step 3: job input field
            job_field_xpath = xlRange.Cells[11, 2].Value2.ToString();
            // step 4: search btn
            find_job_xpath = xlRange.Cells[12, 2].Value2.ToString();
            // pop up
            pop_up_close_xpath = xlRange.Cells[13, 2].Value2.ToString();
        }
        void getTestcase(Excel.Range xlRange) {
            int numTestcase = 0;
            for (int row = 1; true; row++) {
                try {
                    if (xlRange.Cells[row, 1].Value2.ToString() == "Last") {
                        numTestcase = row - 2;
                        break;
                    }
                }
                catch {
                    numTestcase = row - 2;
                    break;
                }
            }
            testcase_list = new Testcase[numTestcase];
            for (int i = 0; i < numTestcase; i++) {
                string l1 = xlRange.Cells[i + 2, 1].Value2.ToString();
                string l2 = xlRange.Cells[i + 2, 2].Value2.ToString();
                string l3 = xlRange.Cells[i + 2, 3].Value2.ToString();
                string l4 = xlRange.Cells[i + 2, 4].Value2.ToString();
                string sR = xlRange.Cells[i + 2, 5].Value2.ToString();
                string iJ = xlRange.Cells[i + 2, 6].Value2.ToString();
                string cS = xlRange.Cells[i + 2, 7].Value2.ToString();
                string fU = xlRange.Cells[i + 2, 8].Value2.ToString();
                string cM3 = xlRange.Cells[i + 2, 9].Value2.ToString();
                string tN = xlRange.Cells[i + 2, 10].Value2.ToString();
                testcase_list[i] = new Testcase(l1, l2, l3, l4, sR, iJ, int.Parse(cS), fU, int.Parse(cM3), tN);
            }
        }
        void collectData() {
            //Create COM Objects. Create a COM object for everything that is referenced
            Excel.Application xlApp = new Excel.Application();
            Excel.Workbook xlWorkbook = xlApp.Workbooks.Open(@"D:\testcase.xlsx");

            Excel._Worksheet xlWorksheet = xlWorkbook.Sheets[1];
            Excel.Range xlRange = xlWorksheet.UsedRange;
            Excel._Worksheet xlWorksheet2 = xlWorkbook.Sheets[2];
            Excel.Range xlRange2 = xlWorksheet2.UsedRange;

            getEnvi(xlRange);
            getTestcase(xlRange2);

            GC.Collect();
            GC.WaitForPendingFinalizers();

            Marshal.ReleaseComObject(xlRange);
            Marshal.ReleaseComObject(xlWorksheet);
            Marshal.ReleaseComObject(xlRange2);
            Marshal.ReleaseComObject(xlWorksheet2);

            //close and release
            xlWorkbook.Close();
            Marshal.ReleaseComObject(xlWorkbook);

            //quit and release
            xlApp.Quit();
            Marshal.ReleaseComObject(xlApp);
        }
        void testing(Testcase testcase) {
            // load website
            driver = new ChromeDriver();
            driver.Navigate().GoToUrl(web_url);
            sleep(2);
            // step 1: Get location
            getLocationn(testcase.loc_list);
            sleep(0.1f);
            // step 2: select recommend
            if (testcase.selectRecommend != "Null") {
                selectJob(testcase.selectRecommend);
                sleep(2);
            }
            // step 3: input job
            if (testcase.inputJob != "Null") {
                inputJob(testcase.inputJob);
                sleep(0.1f);
            }
            // step 4: click search
            if (testcase.clickSearch == 1) {
                clickSearch();
                sleep(2);
            }

            // checking
            // step 5: check web url
            if (testcase.finalURL != "Null") {
                if (checkWebUrl(testcase.finalURL)) testcase.isPass = 1;
                else testcase.isPass = 2;
            }
            // step 6: check max 3
            if (testcase.checkMax3 == 1) {
                if (checkWarningPlace()) testcase.isPass = 1;
                else testcase.isPass = 2;
            }
            driver.Quit();
            //driver.Manage().Cookies.DeleteAllCookies();

        }
        void printResult() {
            Excel.Application xlApp = new Excel.Application();
            Excel.Workbook xlWorkbook = xlApp.Workbooks.Open(@"D:\testcase.xlsx");

            Excel._Worksheet xlWorksheet = xlWorkbook.Sheets[2];
            Excel.Range xlRange = xlWorksheet.UsedRange;

            int row = 2;

            Console.Write("WEBSITE: ");
            Console.Write(web_url);
            Console.WriteLine("\n===============START TESTING: SEARCH SYSTEM================\n");
            foreach (Testcase testcase in testcase_list) {
                testcase.printInfor();
                string final_state = "";
                if (testcase.isPass == 1) final_state = "PASS";
                else if (testcase.isPass == 2) final_state = "FAIL";
                else final_state = "ISN'T TESTED";
                xlRange.Cells[row++, 11] = final_state;
            }
            Console.WriteLine("\n=============COMPLETE TESTING: SEARCH SYSTEM===============");

            GC.Collect();
            GC.WaitForPendingFinalizers();

            Marshal.ReleaseComObject(xlRange);
            Marshal.ReleaseComObject(xlWorksheet);

            //close and release
            xlWorkbook.Close();
            Marshal.ReleaseComObject(xlWorkbook);

            //quit and release
            xlApp.Quit();
            Marshal.ReleaseComObject(xlApp);
        }
        private void Button_Click(object sender, RoutedEventArgs e) {
            collectData();
            foreach (Testcase testcase in testcase_list) testing(testcase);
            printResult();
        }
    }
}
