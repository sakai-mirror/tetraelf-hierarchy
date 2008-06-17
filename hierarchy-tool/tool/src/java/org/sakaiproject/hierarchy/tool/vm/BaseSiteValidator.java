package org.sakaiproject.hierarchy.tool.vm;

import java.util.regex.Pattern;

import org.sakaiproject.hierarchy.tool.vm.NewSiteCommand.Method;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public abstract class BaseSiteValidator implements Validator
{

	private int maxNameLength = 12;
	private int generateLength = 10;
	private int maxTitleLength = 20;

	public BaseSiteValidator()
	{
		super();
	}

	protected void checkTitle(Errors errors, NewSiteCommand command)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
				"validator.title.empty");
		if (command.getTitle() != null && command.getTitle().length() > maxTitleLength) {
			errors.rejectValue("title", "validator.title.too.long", new Object[] {maxTitleLength}, null);
		}
	}

	protected void checkName(Errors errors, NewSiteCommand command)
	{
		if (Method.CUSTOM.equals(command.getMethod())) {
			String url = command.getName();
			if (url == null || url.length() == 0) {
				errors.rejectValue("name", "validator.name.empty");
			} else {
				if (url.length() > maxNameLength) {
					errors.rejectValue("name", "validator.name.too.long",
							new Object[] { Integer.toString(maxNameLength) }, null);
				}
				if (!Pattern.matches("[a-z0-9][_a-z0-9]*", url)) {
					errors.rejectValue("name", "validator.name.bad.characters");
				}
			}
		} else if (Method.AUTOMATIC.equals(command.getMethod())) {
			String name = generateName(command.getTitle());
			if (name == null || name.length() == 0) {
				errors.rejectValue("name", "error.name.generation");
				command.setMethod(Method.CUSTOM);
			} else {
				command.setName(name);
			}
		}
	}

	protected String generateName(String title)
	{
		if (title == null) {
			return null;
		}
		String tmp = title;
		tmp = tmp.toLowerCase();
		tmp = tmp.replaceAll("[^a-z,0-9,_ ]", "");
		tmp = tmp.replaceAll(" ", "_");
		tmp = tmp.replaceAll("_+", "_");
		tmp = tmp.substring(0, (tmp.length() > generateLength) ? 10 : tmp
				.length());
		if (tmp.endsWith("_")) {
			tmp = tmp.substring(0, tmp.length() - 1);
		}
		return tmp;
	}

	public int getMaxTitleLength()
	{
		return maxTitleLength;
	}

	public void setMaxTitleLength(int maxTitleLength)
	{
		this.maxTitleLength = maxTitleLength;
	}

}