TOP_DIR   = $(PWD)
BUILD_DIR = $(TOP_DIR)/build
PRJ = playground
TOP_MODULE = adder

# Chisel Config
CHISEL_BUILD_DIR      = $(BUILD_DIR)/chisel
CHISEL_BUILD_TOP_VSRC = $(CHISEL_BUILD_DIR)/$(TOP_MODULE).sv
CHISEL_DIR            = $(TOP_DIR)/src
CHISEL_MAIN_DIR       = $(CHISEL_DIR)/main/scala
CHISEL_TEST_DIR       = $(CHISEL_DIR)/test/scala
CHISEL_SRC_PATH       = $(foreach dir, $(shell find $(CHISEL_MAIN_DIR) -maxdepth 5 -type d), $(wildcard $(dir)/*.scala))
CHISEL_TOOL           = Tools.build

verilog: $(CHISEL_BUILD_TOP_VSRC)

$(CHISEL_BUILD_TOP_VSRC): $(CHISEL_SRC_PATH)
	@echo --- verilog start  ---
	@mkdir -p $(CHISEL_BUILD_DIR)
	millw -i $(PRJ).runMain $(CHISEL_TOOL) --split-verilog -td $(CHISEL_BUILD_DIR)
	@echo --- verilog finish ---

test:
	millw -i $(PRJ).test

help:
	millw -i $(PRJ).runMain Elaborate --help

reformat:
	millw -i __.reformat

checkformat:
	millw -i __.checkFormat

clean:
	rm -rf $(BUILD_DIR)

.PHONY: test verilog help reformat checkformat clean
