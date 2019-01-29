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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�\���(WEB3SrvRegiReservedWordDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 �s�p (���u) �V�K�쐬
Revesion History : 2005/10/05 ��؁@@���R�I(SRA) �g�����X�����N�Ή�
Revesion History : 2005/10/18 ��؁@@���R�I(SRA) �t�B�f���e�B�Ή�
Revesion History : 2008/03/14 ����@@����@@(SCS) QTP�A�g�Ή�
Revesion History : 2008/05/19 �Ԑi (���u) �d�l�ύX���f��No.368
Revesion History : 2009/04/23 �Ԑi (���u) �d�l�ύX���f��No.412
Revesion History : 2009/05/20 �đo�g(���u) �d�l�ύX���f��No.417
*/
package webbroker3.srvregi.define;

/**
 * �T�[�r�X���p�\���
 * 
 * @@author �s�p
 * @@version 1.0
 */
public interface WEB3SrvRegiReservedWordDef
{
    /**
     * �\���F�،���ЃR�[�h
     */
    public static final String RESERVED_WORD_INSTITUTION_CODE = "%%INSTITUTION_CODE%%";
   
    /**
     * �\���F���X�R�[�h
     */
    public static final String RESERVED_WORD_BRANCH_CODE = "%%BRANCH_CODE%%";
   
    /**
     * �\���F�ڋq�R�[�h
     */
    public static final String RESERVED_WORD_MAIN_ACCOUNT_CODE = "%%ACCOUNT_CODE%%";
   
    /**
     * �\���F�����R�[�h
     */
    public static final String RESERVED_WORD_PRODUCT_CODE = "%%PRODUCT_CODE%%";
   
    /**
     * �\���F�Í����ڋq�R�[�h
     */
    public static final String RESERVED_WORD_ENCRYPTION_ACCOUNT_CODE = "%%ENCRYPTION_ACCOUNT_CODE%%";
   
    /**
     * �\���F�n�b�V���v�Z����
     */
    public static final String RESERVED_WORD_HASH_CALC_VALUE = "%%HASH_VALUE%%";
   
    /**
     * �\���FToken
     */
    public static final String RESERVED_WORD_TOKEN = "%%TOKEN%%";
   
    /**
     * �\���F�����`���l��
     */
    public static final String RESERVED_WORD_ORDER_CHANEL = "%%CHANNEL%%";
   
    /**
     * �\���F����
     */
    public static final String RESERVED_WORD_TRADER = "%%TRADER_CODE%%";
   
    /**
     * �\���F�M�p�����敪
     */
    public static final String RESERVED_WORD_MARGIN_ACCOUNT_DIV = "%%MARGIN_ACCOUNT%%";
   
    /**
     * �\���F�敨OP�����敪�i��؁j
     */
    public static final String RESERVED_WORD_FUOP_OSE_ACCOUNT_DIV = "%%FUOP_OSE_ACCOUNT%%";
   
    /**
     * �\���F�ڋq��
     */
    public static final String RESERVED_WORD_ACCOUNT_NAME = "%%ACCOUNT_NAME%%";
   
    /**
     * �\���F�N�����iYYYYMMDD�j
     */
    public static final String RESERVED_WORD_YYYYMMDD = "%%YYYYMMDD%%";
   
    /**
     * �\���F�N�����iYYYY-MM-DD-HH-MM�j
     */
    public static final String RESERVED_WORD_YYYYMMDDHHMM = "%%YYYY-MM-DD-HH-MM%%";
    
    /**
     * �\���F�N�����iYYYYMMDDHHMM�j <BR>
     */
    public static final String RESERVED_WORD_YYYYMMDDHHMM_2 = "%%YYYYMMDDHHMM%%";

   
    /**
     * �\���F�_������i�K�p�I�����j
     */
    public static final String RESERVED_WORD_APPLI_EXPIRE_DATE = "%%APPLI_EXPIRE_DATE%%";
   
    /**
     * �\���F�n�b�V���v�Z�v�f�i�P�j
     */
    public static final String RESERVED_WORD_HASH_ELEMENT_1 = "%%HASH_ELEMENT_1%%";
   
    /**
     * �\���F�n�b�V���v�Z�v�f�i�Q�j
     */
    public static final String RESERVED_WORD_HASH_ELEMENT_2 = "%%HASH_ELEMENT_2%%";
   
    /**
     * �\���F���Җ�
     */
    public static final String RESERVED_WORD_TRADER_NAME = "%%TRADER_NAME%%";
   
    /**
     * �\���F���͋敪
     */
    public static final String RESERVED_WORD_INPUT_DIV = "%%INPUT_DIV_(";
   
    /**
     * �\���F���͋敪����
     */
    public static final String RESERVED_WORD_INPUT_DIV_END = ")%%";
   
    /**
     * (�\���F�i��u���j%%HSTR%%)
     * ���،� �C���C�g���[�_�[��p
     */
    public static final String RESERVED_WORD_HSTR = "%%HSTR%%";
   
    /**
     * (�\���F�i��u���j%%FUNDTYPE%%)
     * ���،� �C���C�g���[�_�[��p
     */
    public static final String RESERVED_WORD_FUNDTYPE = "%%FUNDTYPE%%";
   
    /**
     * (�\���F�i��u���j%%FUNDCODE%%)
     * ���،� �C���C�g���[�_�[��p
     */
    public static final String RESERVED_WORD_FUNDCODE = "%%FUNDCODE%%";
   
    /**
     * (�\���F�i��u���j%%DELYEAR%%)
     * ���،� �C���C�g���[�_�[��p
     */
    public static final String RESERVED_WORD_DELYEAR = "%%DELYEAR%%";
   
    /**
     * (�\���F�i��u���j%%DELMONTH%%)
     * ���،� �C���C�g���[�_�[��p
     */
    public static final String RESERVED_WORD_DELMONTH = "%%DELMONTH%%";
   
    /**
     * (�\���F�i��u���j%%PUTCALL%%)
     * ���،� �C���C�g���[�_�[��p
     */
    public static final String RESERVED_WORD_PUTCALL = "%%PUTCALL%%";
   
    /**
     * (�\���F�i��u���j%%STRIKEPRC%%)
     * ���،� �C���C�g���[�_�[��p
     */
    public static final String RESERVED_WORD_STRIKEPRC = "%%STRIKEPRC%%";
   
    /**
     * (�\���F�i��u���j%%TRADETYPE%%)
     * ���،� �C���C�g���[�_�[��p
     */
    public static final String RESERVED_WORD_TRADETYPE = "%%TRADETYPE%%";
   
    /**
     * (�\���F�i��u���j%%BUYSELLFLAG%%)
     * ���،� �C���C�g���[�_�[��p
     */
    public static final String RESERVED_WORD_BUYSELLFLAG = "%%BUYSELLFLAG%%";
   
    /**
     * (�\���F�i��u���j%%STKEXCODE%%)
     * ���،� �C���C�g���[�_�[��p
     */
    public static final String RESERVED_WORD_STKEXCODE = "%%STKEXCODE%%";
    
    /**
     * �\���F�n�b�V�������ꂽ�ڋqID
     */
    public static final String RESERVED_WORD_HASH_ACCOUNT_ID = "%%HASH_ACCOUNT_ID%%";
    
    /**
     * �\���F�s��R�[�h
     */
    public static final String RESERVED_WORD_MARKET_CODE = "%%MARKET_CODE%%";
    
    /**
     * �\���F�^�C�v
     */
    public static final String RESERVED_WORD_TYPE = "%%TYPE%%";
    
    /**
     * �\���FSSID�l
     */
    public static final String RESERVED_WORD_SSID_VALUE = "%%SSID_VALUE%%";
     
    /**
     * �\���F�Í����ۗL�������
     */
    public static final String RESERVED_WORD_ENCRYPTION_MF_ASSET = "%%ENCRYPTION_MF_ASSET%%";
    
    /**
     * �\���F�N�����iYYYYMMDDHHMISS�j
     */
    public static final String RESERVED_WORD_YEAR_MONTH_DAY = "%%YYYYMMDDHHMISS%%";
    
    /**
     * �\���FGUID
     */
    public static final String RESERVED_WORD_GUID = "%%GUID%%";
    
	/**
	 * �\���FID
	 */
	public static final String RESERVED_WORD_ID = "%%ID%%";
	
	/**
	 * �\���FPASS
	 */
	public static final String RESERVED_WORD_PASS = "%%PASS%%";

	/**
	 * ���c�����X�g�FBOND_BALANCE_LIST
	 */
	public static final String RESERVED_WORD_BOND_BALANCE_LIST = "%%BOND_BALANCE_LIST%%";

	/**
	 * �]�͎c�����X�g�FTRADINGPOWER_BALANCE_LIST
	 */
	public static final String RESERVED_WORD_TRADINGPOWER_BALANCE_LIST = "%%TRADINGPOWER_BALANCE_LIST%%";

	/**
	 * ���Y�]���z�ꗗ�FSTOCK_APPRAISAL_VALUE_INSPECTION
	 */
	public static final String RESERVED_WORD_STOCK_APPRAISAL_VALUE_INSPECTION = "%%STOCK_APPRAISAL_VALUE_INSPECTION%%";

	/**
	 * ������p�Í����p�X���[�h�FBOND_ENCRYPT_PASS
	 */
	public static final String RESERVED_WORD_BOND_ENCRYPT_PASS = "%%BOND_ENCRYPT_PASS%%";

	/**
	 * �d�q��URL�FDENSHI_BATO_URL
	 */
	public static final String RESERVED_WORD_DENSHI_BATO_URL = "%%DENSHI_BATO_URL%%";

	/**
	 * ���Z�敪�FRESIDENT
	 */
	public static final String RESERVED_WORD_RESIDENT = "%%RESIDENT%%";

	/**
	 * ���T�[�r�X���X�g�FINFORMATION_SERVICE_LIST
	 */
	public static final String RESERVED_WORD_INFORMATION_SERVICE_LIST = "%%INFORMATION_SERVICE_LIST%%";

    /**
     * ���FX���O�C��ID�FOSE_LOGINID
     */
    public static final String RESERVED_OSE_LOGINID = "%%OSE_LOGINID%%";

    /**
     * ���T�[�r�X�\���󋵁FOTHER_SRV_REGI_STATUS
     */
    public static final String  RESERVED_WORD_OTHER_SRV_REGI_STATUS = "%%OTHER_SRV_REGI_STATUS%%";

    /**
     * �����ŋ敪�FEQUITY_TAXTYPE
     */
    public static final String RESERVED_WORD_EQUITY_TAXTYPE = "%%EQUITY_TAXTYPE%%";

    /**
     * �����ŋ敪�i���N�j�FEQUITY_TAXTYPE_N
     */
    public static final String RESERVED_WORD_EQUITY_TAXTYPE_N = "%%EQUITY_TAXTYPE_N%%";

    /**
     * �M�p�ŋ敪�FMARGIN_TAXTYPE
     */
    public static final String RESERVED_WORD_MARGIN_TAXTYPE = "%%MARGIN_TAXTYPE%%";

    /**
     * �M�p�ŋ敪�i���N�j�FMARGIN_TAXTYPE_N
     */
    public static final String RESERVED_WORD_MARGIN_TAXTYPE_N = "%%MARGIN_TAXTYPE_N%%";

    /**
     * CD�L�[�FCD_KEY
     */
    public static final String RESERVED_WORD_CD_KEY = "%%CD_KEY%%";
}
@
