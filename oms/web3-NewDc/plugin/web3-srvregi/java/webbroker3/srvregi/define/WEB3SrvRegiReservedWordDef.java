head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiReservedWordDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : T[rXp\ñê(WEB3SrvRegiReservedWordDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 sp (u) VKì¬
Revesion History : 2005/10/05 éØ@@üRI(SRA) gXNÎ
Revesion History : 2005/10/18 éØ@@üRI(SRA) tBfeBÎ
Revesion History : 2008/03/14 ¼ä@@ºñ@@(SCS) QTPAgÎ
Revesion History : 2008/05/19 Ôi (u) dlÏXfNo.368
Revesion History : 2009/04/23 Ôi (u) dlÏXfNo.412
Revesion History : 2009/05/20 Äog(u) dlÏXfNo.417
*/
package webbroker3.srvregi.define;

/**
 * T[rXp\ñê
 * 
 * @@author sp
 * @@version 1.0
 */
public interface WEB3SrvRegiReservedWordDef
{
    /**
     * \ñêFØïÐR[h
     */
    public static final String RESERVED_WORD_INSTITUTION_CODE = "%%INSTITUTION_CODE%%";
   
    /**
     * \ñêFXR[h
     */
    public static final String RESERVED_WORD_BRANCH_CODE = "%%BRANCH_CODE%%";
   
    /**
     * \ñêFÚqR[h
     */
    public static final String RESERVED_WORD_MAIN_ACCOUNT_CODE = "%%ACCOUNT_CODE%%";
   
    /**
     * \ñêFÁ¿R[h
     */
    public static final String RESERVED_WORD_PRODUCT_CODE = "%%PRODUCT_CODE%%";
   
    /**
     * \ñêFÃ»ÚqR[h
     */
    public static final String RESERVED_WORD_ENCRYPTION_ACCOUNT_CODE = "%%ENCRYPTION_ACCOUNT_CODE%%";
   
    /**
     * \ñêFnbVvZÊ
     */
    public static final String RESERVED_WORD_HASH_CALC_VALUE = "%%HASH_VALUE%%";
   
    /**
     * \ñêFToken
     */
    public static final String RESERVED_WORD_TOKEN = "%%TOKEN%%";
   
    /**
     * \ñêF¶`l
     */
    public static final String RESERVED_WORD_ORDER_CHANEL = "%%CHANNEL%%";
   
    /**
     * \ñêFµÒ
     */
    public static final String RESERVED_WORD_TRADER = "%%TRADER_CODE%%";
   
    /**
     * \ñêFMpûÀæª
     */
    public static final String RESERVED_WORD_MARGIN_ACCOUNT_DIV = "%%MARGIN_ACCOUNT%%";
   
    /**
     * \ñêFæ¨OPûÀæªiåØj
     */
    public static final String RESERVED_WORD_FUOP_OSE_ACCOUNT_DIV = "%%FUOP_OSE_ACCOUNT%%";
   
    /**
     * \ñêFÚq¼
     */
    public static final String RESERVED_WORD_ACCOUNT_NAME = "%%ACCOUNT_NAME%%";
   
    /**
     * \ñêFNúiYYYYMMDDj
     */
    public static final String RESERVED_WORD_YYYYMMDD = "%%YYYYMMDD%%";
   
    /**
     * \ñêFNúiYYYY-MM-DD-HH-MMj
     */
    public static final String RESERVED_WORD_YYYYMMDDHHMM = "%%YYYY-MM-DD-HH-MM%%";
    
    /**
     * \ñêFNúiYYYYMMDDHHMMj <BR>
     */
    public static final String RESERVED_WORD_YYYYMMDDHHMM_2 = "%%YYYYMMDDHHMM%%";

   
    /**
     * \ñêF_ñúÀiKpI¹új
     */
    public static final String RESERVED_WORD_APPLI_EXPIRE_DATE = "%%APPLI_EXPIRE_DATE%%";
   
    /**
     * \ñêFnbVvZvfiPj
     */
    public static final String RESERVED_WORD_HASH_ELEMENT_1 = "%%HASH_ELEMENT_1%%";
   
    /**
     * \ñêFnbVvZvfiQj
     */
    public static final String RESERVED_WORD_HASH_ELEMENT_2 = "%%HASH_ELEMENT_2%%";
   
    /**
     * \ñêFµÒ¼
     */
    public static final String RESERVED_WORD_TRADER_NAME = "%%TRADER_NAME%%";
   
    /**
     * \ñêFüÍæª
     */
    public static final String RESERVED_WORD_INPUT_DIV = "%%INPUT_DIV_(";
   
    /**
     * \ñêFüÍæªö
     */
    public static final String RESERVED_WORD_INPUT_DIV_END = ")%%";
   
    /**
     * (\ñêFiñu·j%%HSTR%%)
     * âäØ CCg[_[êp
     */
    public static final String RESERVED_WORD_HSTR = "%%HSTR%%";
   
    /**
     * (\ñêFiñu·j%%FUNDTYPE%%)
     * âäØ CCg[_[êp
     */
    public static final String RESERVED_WORD_FUNDTYPE = "%%FUNDTYPE%%";
   
    /**
     * (\ñêFiñu·j%%FUNDCODE%%)
     * âäØ CCg[_[êp
     */
    public static final String RESERVED_WORD_FUNDCODE = "%%FUNDCODE%%";
   
    /**
     * (\ñêFiñu·j%%DELYEAR%%)
     * âäØ CCg[_[êp
     */
    public static final String RESERVED_WORD_DELYEAR = "%%DELYEAR%%";
   
    /**
     * (\ñêFiñu·j%%DELMONTH%%)
     * âäØ CCg[_[êp
     */
    public static final String RESERVED_WORD_DELMONTH = "%%DELMONTH%%";
   
    /**
     * (\ñêFiñu·j%%PUTCALL%%)
     * âäØ CCg[_[êp
     */
    public static final String RESERVED_WORD_PUTCALL = "%%PUTCALL%%";
   
    /**
     * (\ñêFiñu·j%%STRIKEPRC%%)
     * âäØ CCg[_[êp
     */
    public static final String RESERVED_WORD_STRIKEPRC = "%%STRIKEPRC%%";
   
    /**
     * (\ñêFiñu·j%%TRADETYPE%%)
     * âäØ CCg[_[êp
     */
    public static final String RESERVED_WORD_TRADETYPE = "%%TRADETYPE%%";
   
    /**
     * (\ñêFiñu·j%%BUYSELLFLAG%%)
     * âäØ CCg[_[êp
     */
    public static final String RESERVED_WORD_BUYSELLFLAG = "%%BUYSELLFLAG%%";
   
    /**
     * (\ñêFiñu·j%%STKEXCODE%%)
     * âäØ CCg[_[êp
     */
    public static final String RESERVED_WORD_STKEXCODE = "%%STKEXCODE%%";
    
    /**
     * \ñêFnbV»³ê½ÚqID
     */
    public static final String RESERVED_WORD_HASH_ACCOUNT_ID = "%%HASH_ACCOUNT_ID%%";
    
    /**
     * \ñêFsêR[h
     */
    public static final String RESERVED_WORD_MARKET_CODE = "%%MARKET_CODE%%";
    
    /**
     * \ñêF^Cv
     */
    public static final String RESERVED_WORD_TYPE = "%%TYPE%%";
    
    /**
     * \ñêFSSIDl
     */
    public static final String RESERVED_WORD_SSID_VALUE = "%%SSID_VALUE%%";
     
    /**
     * \ñêFÃ»ÛLÁ¿îñ
     */
    public static final String RESERVED_WORD_ENCRYPTION_MF_ASSET = "%%ENCRYPTION_MF_ASSET%%";
    
    /**
     * \ñêFNúiYYYYMMDDHHMISSj
     */
    public static final String RESERVED_WORD_YEAR_MONTH_DAY = "%%YYYYMMDDHHMISS%%";
    
    /**
     * \ñêFGUID
     */
    public static final String RESERVED_WORD_GUID = "%%GUID%%";
    
	/**
	 * \ñêFID
	 */
	public static final String RESERVED_WORD_ID = "%%ID%%";
	
	/**
	 * \ñêFPASS
	 */
	public static final String RESERVED_WORD_PASS = "%%PASS%%";

	/**
	 * ÂcXgFBOND_BALANCE_LIST
	 */
	public static final String RESERVED_WORD_BOND_BALANCE_LIST = "%%BOND_BALANCE_LIST%%";

	/**
	 * ]ÍcXgFTRADINGPOWER_BALANCE_LIST
	 */
	public static final String RESERVED_WORD_TRADINGPOWER_BALANCE_LIST = "%%TRADINGPOWER_BALANCE_LIST%%";

	/**
	 * Y]¿zêFSTOCK_APPRAISAL_VALUE_INSPECTION
	 */
	public static final String RESERVED_WORD_STOCK_APPRAISAL_VALUE_INSPECTION = "%%STOCK_APPRAISAL_VALUE_INSPECTION%%";

	/**
	 * ÂæøpÃ»pX[hFBOND_ENCRYPT_PASS
	 */
	public static final String RESERVED_WORD_BOND_ENCRYPT_PASS = "%%BOND_ENCRYPT_PASS%%";

	/**
	 * dqµURLFDENSHI_BATO_URL
	 */
	public static final String RESERVED_WORD_DENSHI_BATO_URL = "%%DENSHI_BATO_URL%%";

	/**
	 * ZæªFRESIDENT
	 */
	public static final String RESERVED_WORD_RESIDENT = "%%RESIDENT%%";

	/**
	 * îñT[rXXgFINFORMATION_SERVICE_LIST
	 */
	public static final String RESERVED_WORD_INFORMATION_SERVICE_LIST = "%%INFORMATION_SERVICE_LIST%%";

    /**
     * åØFXOCIDFOSE_LOGINID
     */
    public static final String RESERVED_OSE_LOGINID = "%%OSE_LOGINID%%";

    /**
     * ¼T[rX\óµFOTHER_SRV_REGI_STATUS
     */
    public static final String  RESERVED_WORD_OTHER_SRV_REGI_STATUS = "%%OTHER_SRV_REGI_STATUS%%";

    /**
     * »¨ÅæªFEQUITY_TAXTYPE
     */
    public static final String RESERVED_WORD_EQUITY_TAXTYPE = "%%EQUITY_TAXTYPE%%";

    /**
     * »¨ÅæªiNjFEQUITY_TAXTYPE_N
     */
    public static final String RESERVED_WORD_EQUITY_TAXTYPE_N = "%%EQUITY_TAXTYPE_N%%";

    /**
     * MpÅæªFMARGIN_TAXTYPE
     */
    public static final String RESERVED_WORD_MARGIN_TAXTYPE = "%%MARGIN_TAXTYPE%%";

    /**
     * MpÅæªiNjFMARGIN_TAXTYPE_N
     */
    public static final String RESERVED_WORD_MARGIN_TAXTYPE_N = "%%MARGIN_TAXTYPE_N%%";

    /**
     * CDL[FCD_KEY
     */
    public static final String RESERVED_WORD_CD_KEY = "%%CD_KEY%%";
}
@
