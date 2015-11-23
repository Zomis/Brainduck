package net.zomis.brainf;

import net.zomis.brainf.analyze.InspectionResult;
import net.zomis.brainf.analyze.analyzers.PlusMinusOptimizer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class OptimizeTest extends BrainfuckTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"+>+++<++>--<->+>", "++>++>"},
                {">>>+++<<< <+> >>--", "<+>>>-->+++<"}, // -1, +3, end at +2. from right to pos
                {"<<<<< + >>>>>   >+< <", null}, // -5, +1, end at -1. from right to pos
                {"+>+", null},
                {"+-", ""},
                {"<>", ""},
                {"+-<>", ""},
                {"+>-<->+<", ""},
                {"+>-<+>-<", "++>--<"},
                {"<+>-<+>-", "<++>--"},
                {">>-<+>", "+>->"},

        });
    }

    @Parameterized.Parameter(0)
    public String original;

    @Parameterized.Parameter(1)
    public String cleaned;

    @Test
    public void test() {
        getSource().addCommands(original);
        analyze(new PlusMinusOptimizer());
        System.out.println(getAnalyze().getInspectionResults());
        List<InspectionResult> results = getAnalyze().getInspectionResults();
        String actual = results.isEmpty() ? null : results.get(0).getDescription();
        String str = String.format("%s resulted in %s and not the expected %s", original, actual, cleaned);
        if (cleaned == null) {
            Assert.assertTrue(str, getAnalyze().getInspectionResults().isEmpty());
            return;
        }
        Assert.assertFalse(str, getAnalyze().getInspectionResults().isEmpty());
        Assert.assertTrue(str, getAnalyze().getInspectionResults().get(0).getDescription()
                .contains(cleaned));
    }



}
