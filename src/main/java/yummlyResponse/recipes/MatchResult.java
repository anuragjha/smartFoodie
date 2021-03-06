/**
 * 
 */
package yummlyResponse.recipes;

import htmlGenerator.HTML;

/**
 * @author anuragjha
 *
 */
public class MatchResult {

	imageUrlsBySize90 imageUrlsBySize;
	String sourceDisplayName;
	String[] ingredients;
	String id;
	String[] smallImageUrls;
	String recipeName;
	int totalTimeInSeconds;
	attribute attributes;
	flavor flavors;
	int rating;

	public String toHTML() {

		StringBuilder matchResultHtml = new StringBuilder();

		matchResultHtml.append(HTML.trOpen());
		
		//get Recipe
		matchResultHtml.append(HTML.tdOpen());
		matchResultHtml.append(HTML.formOpen("/recipesearch/"+id, HTML.FORMMETHOD.GET));
		matchResultHtml.append(HTML.submit(">>"));	    
		matchResultHtml.append(HTML.formClose());
//		matchResultHtml.append(HTML.td(id));
		
		matchResultHtml.append(HTML.tdClose());
		
		matchResultHtml.append(HTML.td(recipeName));
		//matchResultHtml.append(HTML.td(imageUrlsBySize.toString()));
		//matchResultHtml.append(HTML.td(sourceDisplayName));
		matchResultHtml.append(HTML.tdOpen());
		//for(String ingredient : ingredients) {
		matchResultHtml.append(ingredients[0]);
		for(int i=1; i < ingredients.length; i++) {
			matchResultHtml.append(", " + ingredients[i]);
		}
		
		matchResultHtml.append(HTML.tdClose());
		//matchResultHtml.append(HTML.td(id));
		//matchResultHtml.append(HTML.tdOpen());
		//for(String smallImageUrl : smallImageUrls) {
		//	matchResultHtml.append(smallImageUrl + "  ");
		//}
		//matchResultHtml.append(HTML.tdClose());
		
		matchResultHtml.append(HTML.td(Integer.toString(totalTimeInSeconds/60)));
		matchResultHtml.append(HTML.preBuiltHtml(attributes.toHTML()));
		
//		if(flavors != null) {
//			matchResultHtml.append(HTML.preBuiltHtml(flavors.toHTML()));
//		}
		
		matchResultHtml.append(HTML.td(Integer.toString(rating)));

		matchResultHtml.append(HTML.trClose());


		return matchResultHtml.toString();
	}


	public static String fieldNamesAsHtmlTableHeaders() {
		StringBuilder htmlTableHeaders = new StringBuilder();
		htmlTableHeaders.append(HTML.trOpen());
		htmlTableHeaders.append(HTML.th("Get Recipe"));  ///  /// ///  /// submit buttons
		htmlTableHeaders.append(HTML.th("Recipe Name"));
		//htmlTableHeaders.append(HTML.th("imageUrlsBySize"));
		//htmlTableHeaders.append(HTML.th("sourceDisplayName"));
		htmlTableHeaders.append(HTML.th("Ingredients"));
		//htmlTableHeaders.append(HTML.th("id"));
		//htmlTableHeaders.append(HTML.th("smallImageUrls"));
		
		htmlTableHeaders.append(HTML.th("Prep Time(Mins)"));
		htmlTableHeaders.append(HTML.th("course"));
		htmlTableHeaders.append(HTML.th("cuisine"));
		//htmlTableHeaders.append(HTML.th("flavors"));
		htmlTableHeaders.append(HTML.th("rating"));
		htmlTableHeaders.append(HTML.trClose());
		
		
		return htmlTableHeaders.toString();
	}

	/**
	 * 
	 */
	public MatchResult() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
