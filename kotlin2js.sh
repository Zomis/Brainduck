#!/bin/bash

cd brainduck-kotlin && ./gradlew jsBrowserProductionLibraryDistribution && cd ..
cp brainduck-kotlin/worker/build/dist/js/productionLibrary/*.mjs frontend/public/worker/
