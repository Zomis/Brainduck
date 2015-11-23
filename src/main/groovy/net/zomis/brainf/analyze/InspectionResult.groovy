package net.zomis.brainf.analyze

class InspectionResult {

    public enum InspectionSeverity {
        HINT, WARNING, ERROR;
    }

    final InspectionSeverity severity

    final int commandIndexStart
    final int commandIndexEnd

    final String description

    InspectionResult(InspectionSeverity severity, int start, int end, String description) {
        this.severity = severity
        this.commandIndexStart = start
        this.commandIndexEnd = end
        this.description = description
    }

}
