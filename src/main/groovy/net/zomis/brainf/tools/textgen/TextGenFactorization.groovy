package net.zomis.brainf.tools.textgen

class TextGenFactorization {

    /*
+++++ +++++ [-
    >+++++ ++
    >+++++ +++++
    >+++++ +++++ ++
    >+++++ +++++ ++
<<<<]
>
>+++++
>++
>++
<<<

Factor: a*b+c
Code length: a + SUM(b) + SUM(c) + 3 + NUM_CHARS * 3
Runtime length: a * LOOP + 1 + AFTER where:
  LOOP is 2 + SUM(b) + NUM_CHARS * 2
  AFTER is SUM(c) + NUM_CHARS


*/
/*        Factor: a*b+c
        Code length: a + SUM(b) + SUM(c) + 3 + NUM_CHARS * 3
        Runtime length: a * LOOP + 1 + AFTER where:
        LOOP is 2 + SUM(b) + NUM_CHARS * 2
        AFTER is SUM(c) + NUM_CHARS*/
    static int calcB(int a, int value) {
        int div = value / a
        int minDiff = Integer.MAX_VALUE
        int bestValue = 0
        for (int b = div - 1; b <= div; b++) {
            int diffFromTarget = Math.abs(value - a * div)
            if (diffFromTarget < minDiff) {
                minDiff = diffFromTarget
                bestValue = b
            }
        }
        bestValue
    }

    static int[] calcC(int a, int[] bs, int[] values) {
        int[] cs = new int[values.length]
        for (int i = 0; i < values.length; i++) {
            int b = bs[i]
            int value = values[i]
            int diffFromTarget = value - a * b
            cs[i] = diffFromTarget
        }
        cs
    }

    static int factorize(int[] values) {
        int best = 0
        int bestScore = Integer.MAX_VALUE
        int max = Arrays.stream(values).max().getAsInt()
        int numChars = values.length

        for (int a = 1; a < max; a++) {
            int[] bs = calcBs(a, values)
            int[] cs = calcC(a, bs, values)

            int bSum = bs.sum()
            int cSum = cs.sum()
            int codeLength = a + bSum + cSum + 3 + numChars * 3
            int loopRuntimeLength = 2 + bSum + numChars * 2
            int afterLoopRuntime = cSum + numChars
            int runtimeLength = a * loopRuntimeLength + 1 + afterLoopRuntime
            int score = codeLength + runtimeLength
            if (score < bestScore) {
                bestScore = score
                best = a
            }
        }
        best
    }

    static int[] calcBs(int a, int[] values) {
        Arrays.stream(values).map({calcB(a, it)}).toArray()
    }
}
