package org.example.wordcounter.app.cli.options;

import java.util.List;

public final class Constants {

	public static final String INPUT_PATH_OPTION = "--input-path";
	public static final String OUTPUT_PATH_OPTION = "--output-path";
	public static final String ENCODING_OPTION = "--encoding";
	public static final String GROUP_SIZE_OPTION = "--group-size";
	public static final String HELP_OPTION = "--help";
	public static final String LANGUAGE_OPTION = "--language";

	public static final String CUSTOM_LANGUAGE_NAME = "CUSTOM";
	public static final Language CZ = Language.of("CZ", "ěščřžýáíéóúůďťňĎŇŤŠČŘŽÝÁÍÉÚŮ\\p{Alpha}");
	public static final Language EN = Language.of("EN", "-'éç\\p{Alpha}");
	public static final Language ES = Language.of("ES", "-'áéíñóúüÁÉÍÑÓÚÜ\\p{Alpha}");
	public static final Language PT = Language.of("PT", "-'áéíóúàãõâêüçÁÉÍÓÚÀÃÕÂÊÜÇ\\p{Alpha}");
	public static final List<Language> AVAILABLE_LANGUAGES = List.of(EN, CZ, ES, PT);

}
