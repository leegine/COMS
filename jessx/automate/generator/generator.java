package jessx.automate.generator;

/**
 * <p>Titre : </p>
 *
 * <p>Description : </p>
 *
 * <p>Copyright : Copyright (c) 2005, Licence GPL, </p>
 *
 * <p>Société : Ec-lille, USTL</p>
 *
 * @author Guillaume Tromp, Etienne Broutin
 * @version 1.0
 */


/***************************************************************/
/*                       IMPORT SECTION                        */
/***************************************************************/

import java.io.*;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;

import org.apache.log4j.*;
import jessx.business.*;
import jessx.server.gui.*;
import jessx.utils.*;
import org.jdom.Element;
import jessx.automate.generator.AssetActivity;
import jessx.automate.AutomateGeneralParameter;
import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import jessx.business.institutions.OrderMarket;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.*;

/***************************************************************/
/*                   Automate CLASS SECTION                    */
/***************************************************************/

public class generator {

// General Parameters
  private AutomateGeneralParameter ourparamaters;



// Assets
  private Asset A [];
  private final String asset_Name[]={"Elf",
      "Total",
      "Cofidis",
      "Michelin",
      "France Telecom",
      "Carrefour",
      "L'Oreal",
      "General Motors",
      "Stock"};

  private int numberAsset;

  String deleteOrder = "Delete Order";
  String limitOrder = "Limit Order";


// Stock Exchanges
  String sE[]={"Paris",
      "New-York",
      "London"};

// Players
  String defaultPlayerName="Gamer";

// Scenario
  Scenario ourscenario;

  private void GenerateGeneralParameters()  {

   System.out.println("Setting General Parameters ...");

    ourparamaters.setPeriodCount((int) (4 * Math.random() + 6));
    //choose the number of period between 6 and 10

    ourparamaters.setPeriodDuration((int) (120 * Math.random() + 60));
     //choose the time of period between 60 and 180

    ourparamaters.setInterestRate((float) (4 * Math.random()));
    // Interest rate between 0 and 4%
  }

  private void GenerateAssets()  {

    System.out.println("Generating Assets ...");

    numberAsset = (int) (4 * Math.random() + 3);

    A = new Asset[numberAsset];

    // Pour l'instant, il les prend dans l'odre. On choisira et vérifira l'unicité après

    for (int i=0;i<numberAsset;i++)
    {
      try {
        A[i] = AssetCreator.createAsset("Stock");
        A[i].setAssetName(asset_Name[i]);

        BusinessCore.addAsset(A[i]);
      }
      catch (AssetNotCreatedException ex) {
        System.out.println("Failed to create an asset");
      }
    }
  }

  private void GenerateStockExchanges() {

     System.out.println("Generating StockExchanges ...");

  }

  private void GeneratePlayers() {

    System.out.println("Generating Players ...");

    final int nbplayertype=1;

    for (int i=0;i<nbplayertype;i++)
    {
      //Add a new player category
      PlayerType player = new PlayerType(defaultPlayerName + " " + i);

      // Create his Portfolio
      Portfolio portfolioPlayer = new Portfolio();

      // Fill his portfolio
      portfolioPlayer.setCash((float) (12000 * Math.random() + 8000));
      //Cash randomly chosen between 8000$ and 20000$
     //The rest of the datas will be completed for each asset, after having created each assets and institutions randomly

      // Give it to him
      player.setPortfolio(portfolioPlayer);

      //Add that type of player
      ourscenario.addPlayerType(player);
   }
  }

  /**
   * Initialize the global data of the scenario
   */
  private void GenerateScenario() {

    System.out.println("Generating Scenario ...");
      //Initialize the global data of the scenario

  }

  /**
   * Create all the parameters needed to have a complete scenario
   */
  private void GenerateInstitutions() {

    System.out.println("Generating Institutions ...");

    boolean tab[] = new boolean[numberAsset];

    Institution newInstitution;

    for (int k = 0 ; k < numberAsset ; k++ )
    {
      try {
        // GENERAL SET UP
        // Picks up a random institution name
//        int AssetChosen=(int) (A.length * Math.random());
        String institutionname = sE[ (int) (sE.length * Math.random())] + " - " +
              A[k].getAssetName();


        // Create the Institution Object
        newInstitution = InstitutionCreator.createInstitution("OrderMarket");

        // Set the Asset name
        newInstitution.setName(institutionname);

        // Link the Asset
        newInstitution.setAsset(A[k]);

        // Send it to BusinessCore
        BusinessCore.addInstitution(newInstitution);


        // OPERATIONS
        // Create Delete Operation

        Operation DO = OperationCreator.createOperation(deleteOrder);

        // Choose the percentage cost of Delete Order
        newInstitution.setPercentageCost(deleteOrder, new Float(5 * Math.random()));

        // Choose the minimal cost of Delete Order
        newInstitution.setMinimalCost(deleteOrder, new Float(5 * Math.random()));

        // Create Limit Operation
        Operation LO = OperationCreator.createOperation(limitOrder);

        // Choose the percentage cost of Limit Order
        newInstitution.setPercentageCost(limitOrder,
                                         new Float(0.5 * Math.random()));

        // Choose the minimal cost of Limit Order
        newInstitution.setMinimalCost(limitOrder, new Float(5 * Math.random()));

        // TRADERS
        // Set his name
        String traderName = ("Trader on "+institutionname);

        // Choose orderBookVisibility between 1 and 7
        int orderBookVisibility = Math.min(ourparamaters.getPeriodCount(),
                                           (int) (6 * Math.random() + 1));

        // Create Ops list
        Vector grantedOps = new Vector();

        // Can now create Operators
        Operator newOperator = new Operator(newInstitution.getName(), traderName,
                                            grantedOps, orderBookVisibility);

        // inutile
        // newOperator.setOrderbookVisibility(orderBookVisibility);

        // Chose his rights
        switch ( (int) (Math.random() * 2)) {
          case 0:
            newOperator.grant(deleteOrder);
            newOperator.grant(limitOrder);
            break;
          case 1:
            newOperator.grant(limitOrder);
            break;
        }

        // Add the Operators created  to the institution
        newInstitution.addOperator(newOperator);


        // In order to find every player
        // Get the player types
        HashMap playertypes = ourscenario.getPlayerTypes();

        // Get the entries
        Set entries = playertypes.entrySet() ;

        // Get the first element
        Iterator iter = entries.iterator();

        //Add the Operator randomly chosen for each institution
        while (iter.hasNext())
        {
          // Get the Entry
          Map.Entry entry = (Map.Entry) iter.next();

          // Get the key
          Object key = entry.getKey();

          // Get the Matching PlayerType
          PlayerType Player = (PlayerType) ourscenario.getPlayerTypes().get(key);

          Player.addOperatorPlayed(newOperator);

          //Amount of Asset randomly chosen between 30 and 160
          Player.getPortfolio().setOwnings(A[k].getAssetName(),
                                     (int) (130 * Math.random() + 30));

          //Table of dividend for each asset
          DividendLimitation DivLimPlay = new DividendLimitation(Player.
              getPlayerTypeName(), A[k].getAssetName());

          DivLimPlay.setDividendDetailledproperties( (int) (3 * Math.random()));
          //0 : Dividends shown for the experiment
          //1 : Dividends shown for the next period
          //2 : Dividends shown for the window
          //3 : Do not show anything

          // Choose a Windows Size between 0 and 2
          DivLimPlay.setWindowSize((int) (5 * Math.random())+1);

          // Choose wether to display windowssize or not
          DivLimPlay.setDisplayWindowSize((Math.random() > 0.5 ));

          // Choose wether to display SessionLength or not
          DivLimPlay.setDisplaySessionLength((Math.random() > 0.5 ));

          // Choose wether to disp OperationsCosts or not
          DivLimPlay.setDisplayOperationsCosts((Math.random() > 0.5 ));

          // Choose wether to display HoldingValueForWindow or not
          DivLimPlay.setDisplayHoldingValueForWindow((Math.random() > 0.5 ));

          //Add the Dividend Limitation to the PlayerType created
          Player.setDividendInfo(A[k].getAssetName(),DivLimPlay);

        }

        // Create a DividendModel
        DividendModel DivModelOurParameters = new DividendModel();
        A[k].setDividendModel(DivModelOurParameters);
        //Complete the dividends (mean and variance) for each period
        DivModelOurParameters.setPeriodCount(ourparamaters.getPeriodCount());

        //Create only a 2 numbers precision after the coma for prices
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        NumberFormat formater3 =
        new DecimalFormat(".##", dfs);

    int Probability= (int) (100 * Math.random());
        //Asset (50% of chance)
        if (Probability> 50 ) {
            //Mean standart randomly chosen between 20 and 90 with a precision of 2 numbers after the coma
            float BaseAssetPrice = (float)(70 * Math.random() + 20);

        for (int throughDivPeriod = 0; throughDivPeriod < (ourparamaters.getPeriodCount()-1);
               throughDivPeriod++) {

             Dividend div = DivModelOurParameters.getDividendAt( throughDivPeriod );

             float VariationBaseAssetPrice =(float)(20 * Math.random() - 20);
             String FinalValue3 =formater3.format(BaseAssetPrice+VariationBaseAssetPrice);
             float FinalValue2 =Float.parseFloat(FinalValue3);
             Float FinalValue=new Float(FinalValue2);
              div.setParameter(0, FinalValue);

              //Variance randomly chosen between 0 and 7
              div.setParameter(1, new Float(7 * Math.random()));

            }

            //Actualisation of the price of the asset at the last period
           int throughDivPeriod = (ourparamaters.getPeriodCount()-1);
           Dividend div = DivModelOurParameters.getDividendAt( throughDivPeriod );
           float VariationBaseAssetPrice =(float)(20 * Math.random() - 10);
           Float PriceLastPeriod = new Float((BaseAssetPrice+VariationBaseAssetPrice)/ ( (1-1/(1+(ourparamaters.getInterestRate()/100)))));
          div.setParameter(0, PriceLastPeriod);
          div.setParameter(1, new Float(4 * Math.random()));
          }

          //Obligation (30% of chance)
          if ( (Probability<=50) && (Probability >20) ) {
            Float PriceObligation3= new Float(70 * Math.random() + 40);
           String PriceObligation2 = formater3.format(PriceObligation3);
           Float PriceObligation =new Float( Float.parseFloat(PriceObligation2));
            for (int throughDivPeriod = 0; throughDivPeriod < (ourparamaters.getPeriodCount());
               throughDivPeriod++) {

           Dividend div = DivModelOurParameters.getDividendAt( throughDivPeriod );

             //Mean always constant because it's an obligation
             div.setParameter(0, PriceObligation);

             //Variance randomly chosen between 0 and 4
             div.setParameter(1, new Float(4 * Math.random()));

            }
          }


          //Start-up (20% of chance)
          if ( Probability<=20) {
            int CrashPeriod = (int) (2 * Math.random()+2);
            int i=1;
             for (int throughDivPeriod = 0; throughDivPeriod < (ourparamaters.getPeriodCount()-CrashPeriod);
               throughDivPeriod++) {

             Dividend div = DivModelOurParameters.getDividendAt( throughDivPeriod );
             float Value =(float)( 20*i);
             i++;
             System.out.println(i);
             Float PriceStartup3 = new Float(20 * Math.random() + Value);
             String PriceStartup2 = formater3.format(PriceStartup3);
             Float PriceStartup = new Float(Float.parseFloat(PriceStartup2));
             div.setParameter(0,PriceStartup);

             //Variance randomly chosen between 0 and 20
             div.setParameter(1, new Float(20 * Math.random()));

           }
           for (int throughDivPeriod = (ourparamaters.getPeriodCount()-CrashPeriod); throughDivPeriod < (ourparamaters.getPeriodCount());       throughDivPeriod++) {
             Dividend div = DivModelOurParameters.getDividendAt( throughDivPeriod );

             //Mean always null because the start-up crashed
             Float Zero =new Float ((float) 0);
             div.setParameter (0,Zero);
             div.setParameter(1,Zero);

           }
      }



      }





      catch (InstitutionNotCreatedException ex1) {
        System.out.println("InstitutionNotCreatedException");
      }

      catch (OperationNotCreatedException ex9) {
         System.out.println("OperationNotCreatedException");
      }
    }
  }

  public void Generate() {

    Load();

    GenerateGeneralParameters();

    GenerateAssets();

    GenerateStockExchanges();

    GenerateScenario();

    GeneratePlayers();

    GenerateInstitutions();
  }


  private void Load() {

      //LOAD
     //Load DeleteOrder
     System.out.println("Loading DeleteOrder...");
        try {
          jessx.business.OperationCreator.operationFactories.put(deleteOrder,
              Class.forName("jessx.business.operations.DeleteOrder"));
        }
        catch (ClassNotFoundException ex2) {
        }
       //Load OrderMarket
       try {
            jessx.business.InstitutionCreator.institutionFactories.put(
              "OrderMarket",
              Class.forName("jessx.business.institutions.OrderMarket"));
            }
            catch (ClassNotFoundException ex) {
            }
     //Load LimitOrder
     System.out.println("Loading LimitOrder...");
       try {
         jessx.business.OperationCreator.operationFactories.put(limitOrder,
             Class.forName("jessx.business.operations.DeleteOrder"));
       }
       catch (ClassNotFoundException ex2) {
       }


  }

  /**
   * Save the file with all the parameters in an XML file named
   * the parameter given in attribut
   * @param name String
   */
  public void Save(String name)  {
    System.out.println("Sauvegarde des parametres sous le nom " + name );

    Document doc = new Document();
    Element experiment = new Element("JessXSetup");
    BusinessCore.saveToXml(experiment);
    doc.setRootElement(experiment);

    try {
      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());

      sortie.output(doc, new FileOutputStream(name));
    }
    catch(IOException a)  {
      System.out.println("Unable to open file for write  " + name );
    }
  }

  /**
   * Launch a generator
   *
   */
  public generator() {
    System.out.println("Lancement du generateur");

    // write here what needs to be set up only once

    ourparamaters = new AutomateGeneralParameter();

    BusinessCore.setGeneralParameters(ourparamaters);

    ourscenario = BusinessCore.getScenario();
  }

}
