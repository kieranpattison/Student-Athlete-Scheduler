package ui;

import model.*;
import persistence.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Integer.parseInt;

//Student-Athlete schedule application
public class ScheduleApp extends JFrame {
    private static final String JSON_WEEK_STORE = "./data/week.json";
    private static final String JSON_TODO_STORE = "./data/taskToDoList.json";
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private Week week;
    private List<TaskToDo> tasks;
    //private Scanner input;
    private JsonWriterTaskToDo jsonWriterTaskToDo;
    private JsonReaderTaskToDoList jsonReaderTaskToDoList;
    private JsonWriterWeek jsonWriterWeek;
    private JsonReaderWeek jsonReaderWeek;
    private JDesktopPane desktopPane;

    //EFFECTS: runs Schedule Application
    public ScheduleApp() {
        //runSchedule();
        init();

    }

//    //MODIFIES: this
//    //EFFECTS: processes user input
//    private void runSchedule() {
//        boolean keepGoing = true;
//        String command = null;
//
//        init();
//
//        while (keepGoing) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//        System.out.println("\nThanks for using me!");
//    }

    // MODIFIES: this
    // EFFECTS: processes user command
//    private void processCommand(String command) {
//        if (command.equals("add assignment")) {
//            addAssignment();
//        } else if (command.equals("work on a task")) {
//            doAssignment();
//        } else if (command.equals("add workout")) {
//            addEvent();
//        } else if (command.equals("view week")) {
//            viewWeek();
//        } else if (command.equals("load schedule")) {
//            loadSchedule();
//        } else if (command.equals("save schedule")) {
//            saveSchedule();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }

    // MODIFIES: this
    // EFFECTS: initializes gui and schedule
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void init() {
        week = new Week();
        tasks = new ArrayList<>();
        jsonReaderWeek = new JsonReaderWeek(JSON_WEEK_STORE);
        jsonReaderTaskToDoList = new JsonReaderTaskToDoList(JSON_TODO_STORE);
        jsonWriterTaskToDo = new JsonWriterTaskToDo(JSON_TODO_STORE);
        jsonWriterWeek = new JsonWriterWeek(JSON_WEEK_STORE);
        desktopPane = new JDesktopPane();

        JWindow splash = new JWindow();
        splash.getContentPane().add(new JLabel("", new ImageIcon("./data/img.png"),
                SwingConstants.CENTER));
        splash.setBounds(200, 200, 1000, 800);
        splash.setVisible(true);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splash.setVisible(false);

        setContentPane(desktopPane);
        setSize(WIDTH, HEIGHT);
        setTitle("Student-Athlete Scheduler");


        createMenuBar();
        renderWeek();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);



    }

    // MODIFIES: this
    // EFFECTS: creates the menu bar
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem(new SaveFileAction()));
        fileMenu.add(new JMenuItem(new LoadFileAction()));
        menuBar.add(fileMenu);

        JMenu addMenu = new JMenu("Add");
        addMenu.add(new JMenuItem(new AddEventAction()));
        addMenu.add(new JMenuItem(new AddAssignmentAction()));
        menuBar.add(addMenu);

        JMenuItem workOnTasks = new JMenuItem(new WorkOnATaskAction());
        menuBar.add(workOnTasks);

        setJMenuBar(menuBar);

    }

    //MODIFIES: this
    //EFFECTS: renders the schedule for the week
    private void renderWeek() {
        desktopPane.removeAll();
        int slot = 0;
        for (Day day : week.getDays()) {
            String labelString = "<html><h2>" + day.getDayName() + "<br><h4>";
            for (ScheduleEvent e: day.getEvents()) {
                labelString += e.getName() + "<br>";
            }
            labelString += "<html>";
            JLabel label = new JLabel(labelString);
            label.setBounds(slot, 0, 125, 500);
            label.setVerticalAlignment(SwingConstants.TOP);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            desktopPane.add(label);
            slot += 125;
        }


    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nChoose what you'd like to do:");
        System.out.println("\tadd assignment");
        System.out.println("\twork on a task");
        System.out.println("\tadd workout");
        System.out.println("\tview week");
        System.out.println("\tload schedule");
        System.out.println("\tsave schedule");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: Adds an assignment to the tasks list
    private void addAssignment(String name, int duration, String dayName) {
//        System.out.println("Enter the name of the assignment: ");
//        String name = input.next();
//        System.out.println("How long will it take in hours?: ");
//        int duration = input.nextInt();
//        System.out.println("Which day of the week is it due?: ");
//        String dayName = input.next();
        for (Day day: week.getDays()) {
            if (dayName.equals(day.getDayName())) {
                day.addEvent(name + " due date", 0, 0, 0, 0);
                tasks.add(new TaskToDo(name, duration));
            }
        }

    }

    private class AddAssignmentAction extends AbstractAction {

        AddAssignmentAction() {
            super("Add Assignment");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog(null, "Name of Assignment?",
                    "Enter Name of Assignment", JOptionPane.QUESTION_MESSAGE);
            String durationString = JOptionPane.showInputDialog(null, "Duration in Hours?",
                    "Enter Duration", JOptionPane.QUESTION_MESSAGE);
            int duration = parseInt(durationString);
            String dayName = JOptionPane.showInputDialog(null, "Day of Week?",
                    "Enter Day of Week", JOptionPane.QUESTION_MESSAGE);
            addAssignment(name, duration, dayName);
            renderWeek();

        }
    }

    //MODIFIES: this
    //EFFECTS: Reduces the duration of time left for an assignment, if finished, removes it from the task list
    private void doAssignment(String taskName, int duration) {
        //System.out.println("Which task would you like to work on?");
        //String taskName = input.next();
        //System.out.println("How long would you like to work on the task for?");
        //int duration = input.nextInt();
        for (TaskToDo t: tasks) {
            if (t.getName().equals(taskName)) {
                if (t.workOnTask(duration)) {
                    tasks.remove(t);
                }
            }

        }
//        System.out.println("There are " + tasks.size() + " tasks left to do");
    }

    // Action for the work on a task menu item
    private class WorkOnATaskAction extends AbstractAction {

        WorkOnATaskAction() {
            super("Work on Tasks");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog(null, "Name of task to work on?",
                    "Enter Which Task", JOptionPane.QUESTION_MESSAGE);
            String durationString = JOptionPane.showInputDialog(null,
                    "How many hours would you like to work on it?", "Enter Duration",
                    JOptionPane.QUESTION_MESSAGE);
            int duration = parseInt(durationString);
            doAssignment(name, duration);
            renderWeek();
        }
    }

    //MODIFIES: this
    //EFFECTS: Adds an event to the schedule
    private void addEvent(String dayName, String name, int startHour, int startMinute, int endHour, int endMinute) {
        //System.out.println("What kind of event would you like to schedule?: ");
        //String name = input.next();
        //System.out.println("What day do you want to schedule it for?: ");
        //String dayName = input.next();
        //System.out.println("What hour does it start at?: ");
        //int startHour = input.nextInt();
        //System.out.println("What minute does it start at?: ");
        //int startMinute = input.nextInt();
        //System.out.println("What hour does it end at?: ");
        //int endHour = input.nextInt();
        //System.out.println("What minute does it end at?: ");
        //int endMinute = input.nextInt();
        for (Day day: week.getDays()) {
            if (dayName.equals(day.getDayName())) {
                day.addEvent(name, startHour, startMinute, endHour, endMinute);
            }
        }
    }

    // Action for the add event menu item
    private class AddEventAction extends AbstractAction {

        AddEventAction() {
            super("Add Event");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String dayName = JOptionPane.showInputDialog(null, "Day of Week?",
                    "Enter day of week for this event", JOptionPane.QUESTION_MESSAGE);
            String name = JOptionPane.showInputDialog(null, "Name of Event?",
                    "Enter name for this event", JOptionPane.QUESTION_MESSAGE);
            String startHourString = JOptionPane.showInputDialog(null, "Start Hour?",
                    "Enter Start Hour", JOptionPane.QUESTION_MESSAGE);
            int startHour = parseInt(startHourString);
            String startMinuteString = JOptionPane.showInputDialog(null, "Start Minute?",
                    "Enter Start Minute", JOptionPane.QUESTION_MESSAGE);
            int startMinute = parseInt(startMinuteString);
            String endHourString = JOptionPane.showInputDialog(null, "End Hour?",
                    "Enter End Hour", JOptionPane.QUESTION_MESSAGE);
            int endHour = parseInt(endHourString);
            String endMinuteString = JOptionPane.showInputDialog(null, "End Minute?",
                    "Enter End Minute", JOptionPane.QUESTION_MESSAGE);
            int endMinute = parseInt(endMinuteString);
            addEvent(dayName, name, startHour, startMinute, endHour, endMinute);
            renderWeek();
        }
    }

    //EFFECTS: Displays the week's schedule
    private void viewWeek() {
        for (Day day: week.getDays()) {
            System.out.println("\n" + day.getDayName() + ": ");
            for (ScheduleEvent scheduleEvent : day.getEvents()) {
                System.out.println(scheduleEvent.getName());
            }
        }
    }

    //EFFECTS: saves the schedule to file
    private void saveSchedule() {
        try {
            jsonWriterWeek.open();
            jsonWriterTaskToDo.open();
            jsonWriterWeek.write(week);
            jsonWriterTaskToDo.write(tasks);
            jsonWriterWeek.close();
            jsonWriterTaskToDo.close();
            System.out.println("Saved schedule to file.");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file.");
        }
    }

    // Action class for the save menu option
    private class SaveFileAction extends AbstractAction {

        SaveFileAction() {
            super("Save Schedule");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveSchedule();
            renderWeek();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads schedule from file
    private void loadSchedule() {
        try {
            week = jsonReaderWeek.read();
            tasks = jsonReaderTaskToDoList.read();
            System.out.println("Loaded schedule from file.");
        } catch (IOException e) {
            System.out.println("Unable to read from file.");
        }
    }

    // Action class for the load menu option
    private class LoadFileAction extends AbstractAction {

        LoadFileAction() {
            super("Load Schedule");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            loadSchedule();
            renderWeek();
        }
    }

    //EFFECTS: overrides the dispose function to print the EventLog and terminate the program
    @Override
    public void dispose() {
        super.dispose();
        printLog();
        System.exit(0);
    }

    // EFFECTS: prints the EventLog
    private void printLog() {
        for (Iterator<Event> it = EventLog.getInstance().iterator(); it.hasNext(); ) {
            Event event = it.next();
            System.out.println(event.getDescription() + " at " + event.getDate());
        }
    }

}
