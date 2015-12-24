package net.zomis.brainf.ui;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import net.zomis.brainf.analyze.AnalyzeFactory;
import net.zomis.brainf.analyze.Brainalyze;
import net.zomis.brainf.analyze.analyzers.BrainfuckAnalyzers;
import net.zomis.brainf.model.*;
import net.zomis.brainf.model.groovy.GroovyBFContext;
import net.zomis.brainf.model.input.ConsoleInput;
import net.zomis.brainf.model.input.ConsoleOutput;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Brainduck {

	public static void main(String[] args) {
        if (args.length == 0) {
            BrainduckFX.startFX(args);
        } else {
            CommandLineOptions options = new CommandLineOptions();
            JCommander jcommander = new JCommander(options);
            try {
                jcommander.parse(args);
            } catch (ParameterException ex) {
                System.out.println(ex.getMessage());
                jcommander.usage();
                return;
            }
            for (String file : options.main) {
                perform(file, options);
            }
        }
	}

    private static void perform(String file, CommandLineOptions options) {
        BrainfuckCode code = new BrainfuckCode();
        code.setSource(ListCode.create(GroovyRead.file(new File(file))));
        BrainfuckRunner runner = new BrainfuckRunner(new BrainfuckMemory(30000), code,
                new ConsoleInput(), new ConsoleOutput());
        if (!options.analyze) {
            runner.run();
        } else {
            Brainalyze analyze = new AnalyzeFactory()
                    .addAnalyzers(BrainfuckAnalyzers.getAvailableAnalyzers())
                    .analyze(runner, new GroovyBFContext());
            analyze.print();
        }
    }

}
