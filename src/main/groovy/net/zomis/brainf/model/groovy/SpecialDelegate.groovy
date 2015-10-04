package net.zomis.brainf.model.groovy

import net.zomis.brainf.model.BrainfuckCodeConverter
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.SubCommand
import net.zomis.brainf.model.classic.BrainfuckConverter

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class SpecialDelegate {

    private BrainfuckRunner runner

    SpecialDelegate(BrainfuckRunner runner) {
        this.runner = runner
    }

    int getValue() {
        runner.memory.getMemory()
    }

    int getPosition() {
        runner.memory.memoryIndex
    }

    void nextLoop(String tagName) {

    }

    void nextLoops(String tagName) {

    }

    void loop(String tagName) {

    }

    void lastLoop(String tagName) {

    }

    def memory(int count) {
        int index = runner.memory.memoryIndex
        [offset: {int forward ->
            int[] values = runner.memory.getMemoryArray(index + forward, count)
            new ArrayBuilder(values)
        }, offsetBackward: {int backward ->
            int[] values = runner.memory.getMemoryArray(index - backward, count)
            new ArrayBuilder(values)
        }]
    }

    ArrayBuilder values(int firstValue) {
        return new ArrayBuilder(firstValue)
    }

    void include(String name) {
        List<URL> urls = []
        if (!name.endsWith('.bf')) {
            name = name + '.bf'
        }
        Path relative = Paths.get(name)
        if (Files.exists(relative)) {
            urls << relative.toUri().toURL()
        }
        urls << getClass().getResource(name)
        urls << getClass().classLoader.getResource(name)

        URL url = urls.stream()
            .peek({url -> println "Checking for $name at $url"})
            .filter({url -> url != null})
            .findFirst()
            .orElseThrow({new RuntimeException("Unable to find file $name")})

        List<BrainfuckCommand> commands = []
        BrainfuckCodeConverter converter = new GroovySupportConverter(new BrainfuckConverter())
        converter.convert(url.text, { commands.add it })
        println 'Subcommand: ' + commands

        def command = new SubCommand(commands)
        command.perform(runner)
    }

}
