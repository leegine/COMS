head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.40.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiReservedWordChange.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�\���ϊ�(WEB3SrvRegiReservedWordChange.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 ���� (���u) �V�K�쐬
Revesion History : 2005/10/05 ��؁@@���R�I(SRA) �g�����X�����N�Ή�
Revesion History : 2005/10/18 ��؁@@���R�I(SRA) �t�B�f���e�B�Ή�
Revesion History : 2005/10/18 �s�p�@@(���u) �d�l�ύX���f��No.230�Ή�
Revesion History : 2008/02/18 ���g (���u) ���f��310
Revesion History : 2008/03/06 ���g (���u) ���f��344,346
Revesion History : 2008/05/22 �Ԑi (���u) ���f��368,374,378,381,385
Revesion History : 2009/04/24 �Ԑi (���u) ���f��405,409
Revesion History : 2009/05/20 �đo�g (���u) ���f��416,418
*/

package webbroker3.srvregi;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.login.service.delegate.WEB3DigestKey;
import webbroker3.login.service.delegate.WEB3DigestService;
import webbroker3.srvregi.define.WEB3SrvRegiApplicationHasDataDef;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStartInfoService;
import webbroker3.tradingpower.service.delegate.WEB3TPBondSimplexCooperationService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �T�[�r�X���p�\���ϊ�
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3SrvRegiReservedWordChange
{

    /**
     * ���O�o�̓��[�e�B���e�B�B <BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3SrvRegiReservedWordChange.class);

    /**
     * (�\���F�،���ЃR�[�h) <BR>
     * �i�萔�j <BR>
     * �\���F�،���ЃR�[�h <BR>
     */
    private static final String RESERVED_WORD_INSTITUTION_CODE = "%%INSTITUTION_CODE%%";

    /**
     * (�\���F���X�R�[�h) <BR>
     * �i�萔�j <BR>
     * �\���F���X�R�[�h <BR>
     */
    private static final String RESERVED_WORD_BRANCH_CODE = "%%BRANCH_CODE%%";

    /**
     * (�\���F�ڋq�R�[�h) <BR>
     * �i�萔�j <BR>
     * �\���F�ڋq�R�[�h <BR>
     */
    private static final String RESERVED_WORD_MAIN_ACCOUNT_CODE = "%%ACCOUNT_CODE%%";

    /**
     * (�\���F�����R�[�h) <BR>
     * �i�萔�j <BR>
     * �\���F�����R�[�h <BR>
     */
    private static final String RESERVED_WORD_PRODUCT_CODE = "%%PRODUCT_CODE%%";

    /**
     * (�\���F�Í����ڋq�R�[�h) <BR>
     * �i�萔�j <BR>
     * �\���F�Í����ڋq�R�[�h <BR>
     */
    private static final String RESERVED_WORD_ENCRYPTION_ACCOUNT_CODE = "%%ENCRYPTION_ACCOUNT_CODE%%";

    /**
     * (�\���F�n�b�V���v�Z����) <BR>
     * �i�萔�j <BR>
     * �\���F�n�b�V���v�Z���� <BR>
     */
    private static final String RESERVED_WORD_HASH_CALC_VALUE = "%%HASH_VALUE%%";

    /**
     * (�\���FToken) <BR>
     * �i�萔�j <BR>
     * �\���FToken <BR>
     */
    private static final String RESERVED_WORD_TOKEN = "%%TOKEN%%";

    /**
     * (�\���F�����`���l��) <BR>
     * �i�萔�j <BR>
     * �\���F�����`���l�� <BR>
     */
    private static final String RESERVED_WORD_ORDER_CHANEL = "%%CHANNEL%%";

    /**
     * (�\���F����) <BR>
     * �i�萔�j <BR>
     * �\���F���� <BR>
     */
    private static final String RESERVED_WORD_TRADER = "%%TRADER_CODE%%";

    /**
     * (�\���F�M�p�����敪) <BR>
     * �i�萔�j <BR>
     * �\���F�M�p�����敪 <BR>
     */
    private static final String RESERVED_WORD_MARGIN_ACCOUNT_DIV = "%%MARGIN_ACCOUNT%%";

    /**
     * (�\���F�敨OP�����敪�i��؁j) <BR>
     * �i�萔�j <BR>
     * �\���F�敨OP�����敪�i��؁j <BR>
     */
    private static final String RESERVED_WORD_FUOP_OSE_ACCOUNT_DIV = "%%FUOP_OSE_ACCOUNT%%";

    /**
     * (�\���F�ڋq��) <BR>
     * �i�萔�j <BR>
     * �\���F�ڋq�� <BR>
     */
    private static final String RESERVED_WORD_ACCOUNT_NAME = "%%ACCOUNT_NAME%%";

    /**
     * (�\���F�N�����iYYYYMMDD�j) <BR>
     * �i�萔�j <BR>
     * �\���F�N�����iYYYYMMDD�j <BR>
     */
    private static final String RESERVED_WORD_YYYYMMDD = "%%YYYYMMDD%%";

    /**
     * (�\���F�N�����iYYYY-MM-DD-HH-MM�j) <BR>
     * �i�萔�j <BR>
     * �\���F�N�����iYYYY-MM-DD-HH-MM�j <BR>
     */
    private static final String RESERVED_WORD_YYYYMMDDHHMM = "%%YYYY-MM-DD-HH-MM%%";
    
    /**
     * (�\���F�N�����iYYYYMMDDHHMM�j) <BR>
     * �i�萔�j <BR>
     * �\���F�N�����iYYYYMMDDHHMM�j <BR>
     */
    private static final String RESERVED_WORD_YYYYMMDDHHMM_2 = "%%YYYYMMDDHHMM%%";

    /**
     * (�\���F�_������i�K�p�I�����j) <BR>
     * �i�萔�j <BR>
     * �\���F�_������i�K�p�I�����j <BR>
     */
    private static final String RESERVED_WORD_APPLI_EXPIRE_DATE = "%%APPLI_EXPIRE_DATE%%";

    /**
     * (�\���F�n�b�V���v�Z�v�f�i�P�j) <BR>
     * �i�萔�j <BR>
     * �\���F�n�b�V���v�Z�v�f�i�P�j <BR>
     */
    private static final String RESERVED_WORD_HASH_ELEMENT_1 = "%%HASH_ELEMENT_1%%";

    /**
     * (�\���F�n�b�V���v�Z�v�f�i�Q�j) <BR>
     * �i�萔�j <BR>
     * �\���F�n�b�V���v�Z�v�f�i�Q�j <BR>
     */
    private static final String RESERVED_WORD_HASH_ELEMENT_2 = "%%HASH_ELEMENT_2%%";

    /**
     * (�\���F���Җ�) <BR>
     * �i�萔�j <BR>
     * �\���F���Җ� <BR>
     */
    private static final String RESERVED_WORD_TRADER_NAME = "%%TRADER_NAME%%";

    /**
     * (�\���F���͋敪) <BR>
     * �i�萔�j <BR>
     * �\���F���͋敪 <BR>
     * <BR>
     * �N���X�}�u�T�[�r�X���p�N�����T�[�r�X�v�̃m�[�g�u���⑫���\���F���͋敪�ɂ� �āv�Q�� <BR>
     */
    private static final String RESERVED_WORD_INPUT_DIV = "%%INPUT_DIV_(";

    /**
     * (�\���F���͋敪����) <BR>
     * �i�萔�j <BR>
     * �\���F���͋敪���� <BR>
     * <BR>
     * �N���X�}�u�T�[�r�X���p�N�����T�[�r�X�v�̃m�[�g�u���⑫���\���F���͋敪�ɂ� �āv�Q�� <BR>
     */
    private static final String RESERVED_WORD_INPUT_DIV_END = ")%%";

    /**
     * (�\���F�i��u���j%%HSTR%%) <BR>
     * ���،� �C���C�g���[�_�[��p <BR>
     * �i�u�������s��Ȃ��\���j <BR>
     */
    private static final String RESERVED_WORD_HSTR = "%%HSTR%%";

    /**
     * (�\���F�i��u���j%%FUNDTYPE%%) <BR>
     * ���،� �C���C�g���[�_�[��p <BR>
     * �i�u�������s��Ȃ��\���j <BR>
     */
    private static final String RESERVED_WORD_FUNDTYPE = "%%FUNDTYPE%%";

    /**
     * (�\���F�i��u���j%%FUNDCODE%%) <BR>
     * ���،� �C���C�g���[�_�[��p <BR>
     * �i�u�������s��Ȃ��\���j <BR>
     */
    private static final String RESERVED_WORD_FUNDCODE = "%%FUNDCODE%%";

    /**
     * (�\���F�i��u���j%%DELYEAR%%) <BR>
     * ���،� �C���C�g���[�_�[��p <BR>
     * �i�u�������s��Ȃ��\���j <BR>
     */
    private static final String RESERVED_WORD_DELYEAR = "%%DELYEAR%%";

    /**
     * (�\���F�i��u���j%%DELMONTH%%) <BR>
     * ���،� �C���C�g���[�_�[��p <BR>
     * �i�u�������s��Ȃ��\���j <BR>
     */
    private static final String RESERVED_WORD_DELMONTH = "%%DELMONTH%%";

    /**
     * (�\���F�i��u���j%%PUTCALL%%) <BR>
     * ���،� �C���C�g���[�_�[��p <BR>
     * �i�u�������s��Ȃ��\���j <BR>
     */
    private static final String RESERVED_WORD_PUTCALL = "%%PUTCALL%%";

    /**
     * (�\���F�i��u���j%%STRIKEPRC%%) <BR>
     * ���،� �C���C�g���[�_�[��p <BR>
     * �i�u�������s��Ȃ��\���j <BR>
     */
    private static final String RESERVED_WORD_STRIKEPRC = "%%STRIKEPRC%%";

    /**
     * (�\���F�i��u���j%%TRADETYPE%%) <BR>
     * ���،� �C���C�g���[�_�[��p <BR>
     * �i�u�������s��Ȃ��\���j <BR>
     */
    private static final String RESERVED_WORD_TRADETYPE = "%%TRADETYPE%%";

    /**
     * (�\���F�i��u���j%%BUYSELLFLAG%%) <BR>
     * ���،� �C���C�g���[�_�[��p <BR>
     * �i�u�������s��Ȃ��\���j <BR>
     */
    private static final String RESERVED_WORD_BUYSELLFLAG = "%%BUYSELLFLAG%%";

    /**
     * (�\���F�i��u���j%%STKEXCODE%%) <BR>
     * ���،� �C���C�g���[�_�[��p <BR>
     * �i�u�������s��Ȃ��\���j <BR>
     */
    private static final String RESERVED_WORD_STKEXCODE = "%%STKEXCODE%%";
    
    /**
     * (�\���F%%HASH_ACCOUNT_ID%%)<BR>
     * �i�萔�j <BR>
     * �\���F�n�b�V�������ꂽ�ڋqID
     */
    private static final String RESERVED_WORD_HASH_ACCOUNT_ID = "%%HASH_ACCOUNT_ID%%";
    
    /**
     * (�\���F%%MARKET_CODE%%)
     * (�萔�j<BR>
     * �\���F�s��R�[�h
     */
    private static final String RESERVED_WORD_MARKET_CODE = "%%MARKET_CODE%%";
    
    /**
     * (�\���F%%TYPE%%)
     * (�萔�j<BR>
     * �\���F�^�C�v
     */
    private static final String RESERVED_WORD_TYPE = "%%TYPE%%";
    
    /**
     * (�\���F%%SSID_VALUE%%)
     * (�萔�j<BR>
     * �\���FSSID�l
     */
    private static final String RESERVED_WORD_SSID_VALUE = "%%SSID_VALUE%%";
    
    /**
     * (�\���F%%ENCRYPTION_MF_ASSET%%)
     * (�萔�j<BR>
     * �\���F�Í����ۗL�������
     */
    private static final String RESERVED_WORD_ENCRYPTION_MF_ASSET = "%%ENCRYPTION_MF_ASSET%%";
    
    /**
     * (�\���F�N�����iYYYYMMDDHHMISS�j)<BR>
     * (�萔�j<BR>
     * �\���F�N�����iYYYYMMDDHHMISS�j
     */
    private static final String RESERVED_YEAR_MONTH_DAY = "%%YYYYMMDDHHMISS%%";
    
    /**
     * (�\���FGUID)<BR>
     * (�萔�j<BR>
     * �\���FGUID
     */
    private static final String RESERVED_GUID = "%%GUID%%";

    /**
     * (�\���FID)<BR>
     * (�萔�j<BR>
     * �\���FID<BR>
     */
    private static final String ID = "%%ID%%";

    /**
     * (�\���FPASS)<BR>
     * (�萔�j<BR>
     * �\���FPASS<BR>
     */
    private static final String PASS = "%%PASS%%";

	/**
	 * (�\���FBOND_BALANCE_LIST)<BR>
     * (�萔�j<BR>
	 * �\���F���c�����X�g<BR>
	 */
	private static final String RESERVED_BOND_BALANCE_LIST = "%%BOND_BALANCE_LIST%%";

	/**
	 * (�\���FTRADINGPOWER_BALANCE_LIST)<BR>
     * (�萔�j<BR>
	 * �\���F�]�͎c�����X�g<BR>
	 */
	private static final String RESERVED_TRADINGPOWER_BALANCE_LIST = "%%TRADINGPOWER_BALANCE_LIST%%";

	/**
	 * (�\���FSTOCK_APPRAISAL_VALUE_INSPECTION)<BR>
     * (�萔�j<BR>
	 * �\���F���Y�]���z�ꗗ<BR>
	 */
	private static final String RESERVED_STOCK_APPRAISAL_VALUE_INSPECTION = "%%STOCK_APPRAISAL_VALUE_INSPECTION%%";

	/**
	 * (�\���FBOND_ENCRYPT_PASS)<BR>
     * (�萔�j<BR>
	 * �\���F������p�Í����p�X���[�h<BR>
	 */
	private static final String RESERVED_BOND_ENCRYPT_PASS = "%%BOND_ENCRYPT_PASS%%";

	/**
	 * (�\���FDENSHI_BATO_URL)<BR>
     * (�萔�j<BR>
	 * �\���F�d�q��URL<BR>
	 */
	private static final String RESERVED_DENSHI_BATO_URL = "%%DENSHI_BATO_URL%%";

	/**
	 * (�\���FRESIDENT)<BR>
     * (�萔�j<BR>
	 * �\���F���Z�敪<BR>
	 */
	private static final String RESERVED_RESIDENT = "%%RESIDENT%%";

	/**
	 * (�\���FINFORMATION_SERVICE_LIST)<BR>
     * (�萔�j<BR>
	 * �\���F���T�[�r�X���X�g<BR>
	 */
	private static final String RESERVED_INFORMATION_SERVICE_LIST = "%%INFORMATION_SERVICE_LIST%%";

    /**
     * (�\���FOSE_LOGINID)<BR>
     * (�萔�j<BR>
     * �\���F���FX���O�C��ID <BR>
     */
    private static final String RESERVED_OSE_LOGINID = "%%OSE_LOGINID%%";

    /**
     * (�\���FOTHER_SRV_REGI_STATUS)<BR>
     * (�萔�j<BR>
     * �\���F���T�[�r�X�\����<BR>
     */
    private static final String RESERVED_OTHER_SRV_REGI_STATUS = "%%OTHER_SRV_REGI_STATUS%%";

    /**
     * (�\���FEQUITY_TAXTYPE)<BR>
     * (�萔�j<BR>
     * �\���F�����ŋ敪<BR>
     */
    private static final String RESERVED_EQUITY_TAXTYPE = "%%EQUITY_TAXTYPE%%";

    /**
     * (�\���FEQUITY_TAXTYPE_N)<BR>
     * (�萔�j<BR>
     * �\���F�����ŋ敪�i���N�j<BR>
     */
    private static final String RESERVED_EQUITY_TAXTYPE_N = "%%EQUITY_TAXTYPE_N%%";

    /**
     * (�\���FMARGIN_TAXTYPE)<BR>
     * (�萔�j<BR>
     * �\���F�M�p�ŋ敪<BR>
     */
    private static final String RESERVED_MARGIN_TAXTYPE = "%%MARGIN_TAXTYPE%%";

    /**
     * (�\���FMARGIN_TAXTYPE_N)<BR>
     * (�萔�j<BR>
     * �\���F�M�p�ŋ敪�i���N�j<BR>
     */
    private static final String RESERVED_MARGIN_TAXTYPE_N = "%%MARGIN_TAXTYPE_N%%";

    /**
     * (�\���FCD_KEY)<BR>
     * (�萔�j<BR>
     * �\���FCD�L�[<BR>
     */
    private static final String RESERVED_CD_KEY = "%%CD_KEY%%";

    /**
     * PASS
     */
    private String pass;

    /**
     * ID
     */
    private String id;

    /**
     * �،���ЃR�[�h
     */
    private String institutionCode;

    /**
     * �T�[�r�X�敪
     */
    private String srvDiv;

    /**
     * ���X�R�[�h
     */
    private String branchCode;

    /**
     * �ڋq�R�[�h
     */
    private String mainAccountCode;

    /**
     * �����R�[�h
     */
    private String productCode;

    /**
     * Token
     */
    private String token;

    /**
     * �����`���l��
     */
    private String orderChanel;

    /**
     * ���҃R�[�h
     */
    private String traderCode;

    /**
     * �M�p�����敪
     */
    private String marginAccountDiv;

    /**
     * �敨OP�����敪�i��؁j
     */
    private String futureOPAccountDiv;

    /**
     * ���ݓ��t
     */
    private Timestamp currentTimestamp;
    
    /**
     * �s��R�[�h
     */
    private String marketCode;
    
    /**
     * �^�C�v
     */
    private String type;
    
    /**
     * �n�b�V���v�Z����
     */
    private String hashCalHowTo;
    
    /**
     * SSID�l
     */
    private String ssidValue;

    /**
     * �⏕����
     */
    private SubAccount subAccount;

    /**
     * �_�C�W�F�X�g�L�[
     */
    private WEB3DigestKey  digestKey;

    /**
     * (�T�[�r�X���p�\���ϊ�) <BR>
     * �i�R���X�g���N�^�j<BR>
     * <BR>
     * �P�j�����̑S�Ă𐶐������C���X�^���X�̓����̑����ɃZ�b�g����B<BR>
     * �Q�jWEB3DigestService�̌Ăяo��<BR>
     * �R�jgetRandomKey���\�b�h�̖߂�l�iWEB3DigestKey�^�̃I�u�W�F�N�g�j��<BR>
     * �@@�_�C�W�F�X�g�L�[�ɃZ�b�g����B<BR>
     * @@param l_strInstitutionCode -
     *            �،���ЃR�[�h
     * @@param l_strSrvDiv -
     *            �T�[�r�X�敪
     * @@param l_strBranchCode -
     *            ���X�R�[�h
     * @@param l_strMainAccountCode -
     *            �ڋq�R�[�h
     * @@param l_strProductCode -
     *            �����R�[�h
     * @@param l_strToken -
     *            Token <BR>
     *            �i���e���N���A�،� ���o�e���R��21�p�p�����[�^�j <BR>
     * @@param l_strOrderChanel -
     *            �����`���l��
     * @@param l_strTraderCode -
     *            ���҃R�[�h
     * @@param l_strMarginAccountDiv -
     *            �M�p�����敪 <BR>
     *            �iPR�w���n�����l�j <BR>
     * @@param l_strFutureOPAccounDiv -
     *            �敨OP�����敪�i��؁j <BR>
     *            �iPR�w���n�����l�j <BR>
     * @@param l_tsCurrentTimestamp -
     *            ���ݓ��t
     * @@param l_strMarketCode<BR>
     *            �s��R�[�h<BR>
     * @@param l_strType<BR>
     *            �^�C�v<BR>
     * @@param l_strHashCalHowTo<BR>
     *            �n�b�V���v�Z����
     * @@param l_strSsidValue<BR>
     *            SSID�l
     * @@param l_strId - (Id)<BR>
     * Id<BR>
     * @@param l_strPass - (Pass)<BR>
     * Pass<BR>
     * @@param l_subAccount<BR>
     *            �⏕����<BR>
     * @@roseuid 41B6616D0096
     */
    public WEB3SrvRegiReservedWordChange(String l_strInstitutionCode, String l_strSrvDiv,
        String l_strBranchCode, String l_strMainAccountCode, String l_strProductCode,
        String l_strToken, String l_strOrderChanel, String l_strTraderCode,
        String l_strMarginAccountDiv, String l_strFutureOPAccounDiv, Timestamp l_tsCurrentTimestamp,
        String l_strMarketCode, String l_strType, String l_strHashCalHowTo, String l_strSsidValue,
        String l_strId, String l_strPass, SubAccount l_subAccount)
    {
        //�P�j�����̑S�Ă𐶐������C���X�^���X�̓����̑����ɃZ�b�g����B
        this.institutionCode = l_strInstitutionCode;
        this.srvDiv = l_strSrvDiv;
        this.branchCode = l_strBranchCode;
        this.mainAccountCode = l_strMainAccountCode;
        this.productCode = l_strProductCode;
        this.token = l_strToken;
        this.orderChanel = l_strOrderChanel;
        this.traderCode = l_strTraderCode;
        this.marginAccountDiv = l_strMarginAccountDiv;
        this.futureOPAccountDiv = l_strFutureOPAccounDiv;
        this.currentTimestamp = l_tsCurrentTimestamp;
        this.marketCode = l_strMarketCode;
        this.type = l_strType;
        this.hashCalHowTo = l_strHashCalHowTo;
        this.ssidValue = l_strSsidValue;
        this.id = l_strId;
        this.pass = l_strPass;
        this.subAccount = l_subAccount;
        
        //�Q�jWEB3DigestService�̌Ăяo��
        WEB3DigestService l_service = (WEB3DigestService) 
            Services.getService(WEB3DigestService.class);
        
        //�R�jgetRandomKey���\�b�h�̖߂�l�iWEB3DigestKey�^�̃I�u�W�F�N�g�j��
        //�_�C�W�F�X�g�L�[�ɃZ�b�g����B
        this.digestKey = l_service.getRandomKey();
    }

    /**
     * (replace�\���) <BR>
     * ����.�o�^�l�̒���"%%�`%%"�ŋ�؂�ꂽ�����񂪑��݂��邩�ǂ����𔻒肵�A <BR>
     * ���݂����ꍇ����"%%�`%%"�ŋ�؂�ꂽ��������Y�����镶����ɒu�������ĕԋp����B <BR>
     * <BR>
     * 1) ����.�o�^�l��"%%�`%%"�ŋ�؂�ꂽ�����񂪑��݂��邩�ǂ����𔻒肵�A <BR>
     * ���݂��Ȃ������ꍇ�A����.�o�^�l��ԋp����B <BR>
     * <BR>
     * 2) ����.�o�^�l�ɑ��݂��Ă����\���ɂ���āA�ȉ��̕�������{�B <BR>
     * <BR>
     * ���h�\���F�،���ЃR�[�h�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|this.�،���ЃR�[�h <BR>
     * <BR>
     * ���h�\���F���X�R�[�h�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|this.���X�R�[�h <BR>
     * <BR>
     * ���h�\���F�ڋq�R�[�h�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|this.�ڋq�R�[�h <BR>
     * <BR>
     * ���h�\���F�����R�[�h�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|this.�����R�[�h <BR>
     * <BR>
     * ���h�\���F�Í����ڋq�R�[�h�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|�T�[�r�X���p�N�����T�[�r�X.get�Í����ڋq�R�[�h()�̖߂�l <BR>
     * [get�Í����ڋq�R�[�h�ɓn������] <BR>
     * �ڋq�R�[�h=����.�ڋq�R�[�h <BR>
     * <BR>
     * ���h�\���F�n�b�V���v�Z���ʁh�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|�T�[�r�X���p�N�����T�[�r�X.create�n�b�V���l()�̖߂�l <BR>
     * [create�n�b�V���l�ɓn������]<BR>
     * �@@�،���ЃR�[�h=this.�،���ЃR�[�h<BR>
     * �@@�T�[�r�X�敪=this.�T�[�r�X�敪<BR>
     * �@@���X�R�[�h=this.���X�R�[�h<BR>
     * �@@�ڋq�R�[�h=this.�ڋq�R�[�h<BR>
     * �@@���ݓ��t=this.���ݓ��t<BR>
     * �@@�s��R�[�h=this.�s��R�[�h<BR>
     * �@@�����R�[�h=this.�����R�[�h<BR>
     * �@@�^�C�v=this.�^�C�v<BR>
     * �@@�_�C�W�F�X�g�L�[=this.�_�C�W�F�X�g�L�[<BR>
     * �@@SSID�l=this.SSID�l<BR>
     * <BR>
     * ���h�\���FToken�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|this.Token <BR>
     * <BR>
     * ���h�\���F�����`���l���h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|this.�����`���l�� <BR>
     * <BR>
     * ���h�\���F���ҁh�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|this.���҃R�[�h <BR>
     * �ithis.���҃R�[�h==null�̏ꍇ�ł����̂܂ܒu��������j <BR>
     * <BR>
     * ���h�\���F�M�p�����敪�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|this.�M�p�����敪 <BR>
     * <BR>
     * ���h�\���F�敨OP�����敪�i��؁j�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|this.�敨OP�����敪�i��؁j <BR>
     * <BR>
     * ���h�\���F�ڋq���h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|�g���A�J�E���g�}�l�[�W��.get�ڋq().get�ڋq�\����()�̖߂�l���Z�b�g����B <BR>
     * [get�ڋq�ɓn������] <BR>
     * �،���ЃR�[�h=����.�،���ЃR�[�h <BR>
     * ���X�R�[�h=����.���X�R�[�h <BR>
     * �����R�[�h=����.�ڋq�R�[�h <BR>
     * <BR>
     * ���h�\���F�N�����iYYYYMMDD�j�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|���ݓ��t(*)��"YYYYMMDD"�̏����ŕϊ��������̂��Z�b�g����B <BR>
     * <BR>
     * ���h�\���F�N�����iYYYY-MM-DD-HH-MM�j�h�̏ꍇ�A�\�����ȉ��̒l�� <BR>
     * �u��������B <BR>
     * �|���ݓ��t(*)��"YYYY-MM-DD-HH-MM"�̏����ŕϊ��������̂��Z�b�g����B <BR>
     * <BR>
     * ���h�\���F�N�����iYYYYMMDDHHMM�j�h�̏ꍇ�A�\�����ȉ��̒l�� �u��������B<BR> 
     * -���ݓ��t��"YYYYMMDDHHMM"�̏����ŕϊ��������̂��Z�b�g����B<BR>
     * <BR>
     * ���h�\���F�_������i�K�p�I�����j�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|�T�[�r�X���p�\���o�^�T�[�r�X.get�T�[�r�X�\���o�^().get�K�p�I����() <BR>
     * �̖߂�l���Z�b�g����B <BR>
     * [get�T�[�r�X�\���o�^�ɓn������] <BR>
     * �،���ЃR�[�h=����.�،���ЃR�[�h <BR>
     * ���X�R�[�h=����.���X�R�[�h <BR>
     * �T�[�r�X�敪=����.�T�[�r�X�敪 <BR>
     * �����R�[�h=����.�ڋq�R�[�h <BR>
     * ����敪="�ʏ�" <BR>
     * �L���敪="�L��" <BR>
     * is�s���b�N=false <BR>
     * <BR>
     * ���h�\���F�n�b�V���v�Z�v�f�P�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|�T�[�r�X���Ǘ�.get�T�[�r�X�}�X�^�[().get�n�b�V���l�ꗗ()���R�[������B <BR>
     * �|�擾�����z�񂩂�ŏ���1�����擾����B <BR>
     * �|�擾�����T�[�r�X���p�L�[�I�u�W�F�N�g.get�T�[�r�X���p�L�[()�̖߂�l���Z�b�g�� ��B<BR>
     * <BR>
     * ���h�\���F�n�b�V���v�Z�v�f�Q�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|�T�[�r�X���Ǘ�.get�T�[�r�X�}�X�^�[().get�n�b�V���l�ꗗ()���R�[������B <BR>
     * �|�擾�����z�񂩂�ŏ�����2���ڂ��擾����B <BR>
     * �|�擾�����T�[�r�X���p�L�[�I�u�W�F�N�g.get�T�[�r�X���p�L�[()�̖߂�l���Z�b�g�� ��B<BR>
     * <BR>
     * ���h�\���F���Җ��h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B <BR>
     * �|����.���҃R�[�h!=null�̏ꍇ�A�ȉ��̎菇�Őݒ肷��B <BR>
     * �i����.���҃R�[�h==null�̏ꍇ�A�h�\���F���Җ��h��null�Œu��������j <BR>
     * �|�g���A�J�E���g�}�l�[�W��.getInstitution()���R�[�����A�،���ЃI�u�W�F�N�g? �擾�B <BR>
     * [getInstitution�ɓn������] <BR>
     * �،���ЃR�[�h=this.�،���ЃR�[�h <BR>
     * �|�g�����Z�I�u�W�F�N�g�}�l�[�W��.getTrader���R�[�����A���҃I�u�W�F�N�g���擾 �B<BR>
     * [getTrader�ɓn������] <BR>
     * �،����=�擾�����،���ЃI�u�W�F�N�g <BR>
     * �g���[�_�[�R�[�h=this.���҃R�[�h <BR>
     * ���X�R�[�h=this.���X�R�[�h <BR>
     * �|�擾�������҃I�u�W�F�N�g����擾�������ҕc�����Z�b�g����B <BR>
     * <BR>
     * ���h�\���F���͋敪�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B(EX3�Q��) <BR>
     * �|this.���҃R�[�h==null�̏ꍇ <BR>
     * �R�[���Z���^�[����̑���ł͖����Ɣ��f���āA�h�\���F���͋敪�h���� <BR>
     * �h�\���F���͋敪�����h�̊ԂɎw�肳�ꂽ���l���Z�b�g����B <BR>
     * �|this.���҃R�[�h!=null�̏ꍇ <BR>
     * �R�[���Z���^�[����̑���Ɣ��f����"1"���Z�b�g����B <BR>
     * <BR>
     * ���h�\���F�n�b�V�������ꂽ�ڋqID�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR> 
     * �|�T�[�r�X���p�N�����T�[�r�X.createAccountCodeHashValue()�̖߂�l <BR>
     * [createAccountCodeHashValue()�ɓn���p�����^] <BR>
     * �n�b�V���v�Z�����F�@@this.�n�b�V���v�Z���� <BR>
     * �،���ЃR�[�h�F�@@this.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F�@@this.���X�R�[�h <BR>
     * �ڋq�R�[�h�F�@@this.�ڋq�R�[�h <BR>
     * <BR>
     * ���h�\���F�s��R�[�h�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR> 
     * �|this.�s��R�[�h <BR>
     * <BR>
     * ���h�\���F�^�C�v�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR> 
     * �|this.�^�C�v <BR>
     * <BR>
     * ���h�\���FSSID�l�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �|this.SSID�l<BR>
     * <BR>
     * ���h�\���F�Í����ۗL�������h�̏ꍇ�A�ȉ��̒l�ɒu��������B 
     * �|�T�[�r�X���p�N�����T�[�r�X.get�Í����ۗL�������i�j�̖߂�l���Z�b�g����B 
     * [get�Í����ۗL�������()�ɓn���p�����^] 
     * �،���ЃR�[�h=����.�،���ЃR�[�h 
     * ���X�R�[�h=����.���X�R�[�h 
     * �����R�[�h=����.�ڋq�R�[�h 
     * <BR>
     * ���h�\���F�N�����iYYYYMMDDHHMISS�j�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �@@�|this.�_�C�W�F�X�g�L�[�̃L�[1(����������b)<BR>
     * <BR>
     * ���h�\���FGUID�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �@@�|this.�_�C�W�F�X�g�L�[�̃L�[3(GUID)<BR>
     * <BR>
     * ���h�\���FID�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �@@�|this.ID<BR>
     * ���h�\���FPASS�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �@@�|this.PASS<BR>
     * ���h�\���F���c�����X�g�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �@@�|�T�[�r�X���p���A�g����.get���c�����X�g()�̖߂�l���Z�b�g����B<BR>  
     * [get���c�����X�g()�ɓn������]<BR>  
     * �@@�⏕����:this.�⏕����<BR>
     * ���⏕������null�̏ꍇ�̓G���[�Ƃ���B<BR> 
     * <BR>
     * ���h�\���F�]�͎c�����X�g�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �@@�|�T�[�r�X���p���A�g����.get�]�͎c�����X�g()�̖߂�l���Z�b�g����B<BR> 
     * [get�]�͎c�����X�g()�ɓn������]<BR>  
     * �@@�⏕�����Fthis.�⏕����<BR>
     * �@@���ݓ��t�Fthis.���ݓ��t<BR>
     * ���⏕������null�̏ꍇ�̓G���[�Ƃ���B<BR> 
     * <BR>
     * ���h�\���F���Y�]���z�ꗗ�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �@@�|���V���v���N�X�A�g�T�[�r�X.get���Y�]���z�ꗗ()�̖߂�l���Z�b�g����B<BR> 
     * [get���Y�]���z�ꗗ()�ɓn������]<BR>  
     * �@@�⏕�����Fthis.�⏕����<BR>
     * ���⏕������null�̏ꍇ�̓G���[�Ƃ���B<BR> 
     * <BR>
     * ���h�\���F������p�Í����p�X���[�h�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �@@�|�T�[�r�X���p���A�g����.get���A�g�p�Í����p�X���[�h()�̖߂�l���Z�b�g����B<BR> 
     * [get���A�g�p�Í����p�X���[�h()�ɓn������]<BR>  
     * �@@�،���ЃR�[�h�F�@@this.�،���ЃR�[�h<BR>  
     * �@@���X�R�[�h�F�@@this.���X�R�[�h<BR>
     * �@@�ڋq�R�[�h�F�@@this.�ڋq�R�[�h<BR>
     * �@@�T�[�r�X�敪�Fthis.�T�[�r�X�敪<BR>
     * �@@���ݓ��t�Fthis.���ݓ��t<BR> 
     * �@@���҃R�[�h�Fthis.���҃R�[�h<BR> 
     * <BR>
     * ���h�\���F�d�q��URL�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A�ڋq�I�u�W�F�N�g���擾<BR> 
     * [get�ڋq�ɓn������]<BR>
     * �@@�،���ЃR�[�h�Fthis.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h�Fthis.���X�R�[�h<BR>
     * �@@�����R�[�h�Fthis.�ڋq�R�[�h<BR>
     * <BR>
     * �@@�|�d�q���V�X�e���ڑ��T�[�r�XImpl.get�d�q���ڑ����()�̖߂�l���Z�b�g����B<BR> 
     * [get�d�q���ڑ����()�ɓn������]<BR>
     * �@@�ڋq�F�g���A�J�E���g�}�l�[�W��.get�ڋq()�̖߂�l<BR>
     * <BR>
     * ���h�\���F���T�[�r�X���X�g�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR> 
     * �|�T�[�r�X���p���A�g����.get���T�[�r�X���X�g()�̖߂�l���Z�b�g����B<BR>  
     * [get���T�[�r�X���X�g�ɓn������]<BR> 
     * �@@�،���ЃR�[�h�Fthis.�،���ЃR�[�h<BR>  
     * �@@���X�R�[�h�Fthis.���X�R�[�h<BR>
     * �@@�����R�[�h�Fthis.�ڋq�R�[�h<BR>
     * �@@���ݓ��t�Fthis.���ݓ��t<BR> 
     * <BR>
     * ���h�\���F���Z�敪�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>  
     * �@@�|�g���A�J�E���g�}�l�[�W��.get�ڋq()�̖߂�l���狏�Z�敪���擾���Z�b�g����B<BR> 
     * [get�ڋq�ɓn������]<BR>  
     * �@@�،���ЃR�[�h�Fthis.�،���ЃR�[�h<BR>  
     * �@@���X�R�[�h�Fthis.���X�R�[�h<BR>
     * �@@�����R�[�h�Fthis.�ڋq�R�[�h<BR>
     * <BR>
     * ���h�\���F���FX���O�C��ID�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �@@�|�T�[�r�X���Ǘ�.get�T�[�r�X�}�X�^�[().get�t���敪()���R�[������B<BR>
     * �@@�|get�t���敪()�̖߂�l=null�̏ꍇ�A�u�t���敪��null�ł��B�v��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@: BUSINESS_ERROR_03160<BR>
     * �@@�|�ȊO�̏ꍇ�Aget�t���敪()�̖߂�l+this.�ڋq�R�[�h.substring(0,6)���Z�b�g����B<BR>
     * <BR>
     * ���h�\���F���T�[�r�X�\���󋵁h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �|�T�[�r�X���Ǘ�.get�T�[�r�X�}�X�^�[().get�t���敪(�T�[�r�X���p�L�[ID)���R�[������B<BR>
     * [get�t���敪�ɓn������]<BR>
     * �@@�T�[�r�X���p�L�[ID : 2�i�Œ�l�j<BR>
     * <BR>
     * �@@�|get�t���敪()�̖߂�l=null�̏ꍇ�A�u�t���敪��null�ł��B�v��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@: BUSINESS_ERROR_03160<BR>
     * <BR>
     * �|�T�[�r�X���p�\���o�^�T�[�r�X.get�T�[�r�X�\���o�^()���R�[������B<BR>
     * [get�T�[�r�X�\���o�^�ɓn������]<BR>
     * �@@�،���ЃR�[�h=this.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h=this.���X�R�[�h<BR>
     * �@@�T�[�r�X�敪=get�t���敪()�̖߂�l<BR>
     * �@@�����R�[�h=this.�ڋq�R�[�h<BR>
     * �@@����敪="�ʏ�"<BR>
     * �@@�L���敪="�L��"<BR>
     * �@@is�s���b�N=false<BR>
     * <BR>
     * �T�[�r�X�\���o�^�e�[�u���Ƀf�[�^�����݂���ꍇ�A�u1�v���Z�b�g����A<BR>
     * ���݂��Ȃ��ꍇ�A�u0�v���Z�b�g����B<BR>
     * <BR>
     * ���h�\���F�����ŋ敪�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.get�ڋq()�̖߂�l����ŋ敪���擾���Z�b�g����B<BR>
     * [get�ڋq�ɓn������]<BR>
     * �@@�،���ЃR�[�h�Fthis.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h�Fthis.���X�R�[�h<BR>
     * �@@�����R�[�h�Fthis.�ڋq�R�[�h<BR>
     * <BR>
     * ���h�\���F�����ŋ敪�i���N�j�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.get�ڋq()�̖߂�l����ŋ敪�i���N�j���擾���Z�b�g����B<BR>
     * [get�ڋq�ɓn������]<BR>
     * �@@�،���ЃR�[�h�Fthis.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h�Fthis.���X�R�[�h<BR>
     * �@@�����R�[�h�Fthis.�ڋq�R�[�h<BR>
     * <BR>
     * ���h�\���F�M�p�ŋ敪�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.get�ڋq()�̖߂�l����M�p����ŋ敪���擾���Z�b�g����B<BR>
     * [get�ڋq�ɓn������]<BR>
     * �@@�،���ЃR�[�h�Fthis.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h�Fthis.���X�R�[�h<BR>
     * �@@�����R�[�h�Fthis.�ڋq�R�[�h<BR>
     * <BR>
     * ���h�\���F�M�p�ŋ敪�i���N�j�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.get�ڋq()�̖߂�l����M�p����ŋ敪�i���N�j��<BR>
     * �@@�@@�擾���Z�b�g����B<BR>
     * [get�ڋq�ɓn������]<BR>
     * �@@�،���ЃR�[�h�Fthis.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h�Fthis.���X�R�[�h<BR>
     * �@@�����R�[�h�Fthis.�ڋq�R�[�h<BR>
     * <BR>
     * ���h�\���FCD�L�[�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B<BR>
     * �@@�|�T�[�r�X���p�N�����T�[�r�X.getCD�L�[()�̖߂�l���擾���Z�b�g����B<BR>
     * [getCD�L�[�ɓn������]<BR>
     * �@@���X�R�[�h�Fthis.���X�R�[�h<BR>
     * �@@�����R�[�h�Fthis.�ڋq�R�[�h<BR>
     * �@@�T�[�r�X�敪�Fthis.�T�[�r�X�敪<BR>
     * �@@�،���ЃR�[�h�Fthis.�،���ЃR�[�h<BR>
     * <BR>
     * ���ȉ��̗\���̏ꍇ�A�u�������s�킸���̂܂܂ɂ���B <BR>
     * �E�\���F�i��u���j%%HSTR%% <BR>
     * �E�\���F�i��u���j%%FUNDTYPE%% <BR>
     * �E�\���F�i��u���j%%FUNDCODE%% <BR>
     * �E�\���F�i��u���j%%DELYEAR%% <BR>
     * �E�\���F�i��u���j%%DELMONTH%% <BR>
     * �E�\���F�i��u���j%%PUTCALL%% <BR>
     * �E�\���F�i��u���j%%STRIKEPRC%% <BR>
     * �E�\���F�i��u���j%%TRADETYPE%% <BR>
     * �E�\���F�i��u���j%%BUYSELLFLAG%% <BR>
     * �E�\���F�i��u���j%%STKEXCODE%% <BR>
     * <BR>
     * 3) �u����̕�����ԋp <BR>
     * 3-1) 2)�Œu��������������ɁA���̗\��ꂪ�c���Ă���ꍇ <BR>
     * �u��������������ɑ΂��āA2)���ēx���{����B <BR>
     * <BR>
     * 3-2) 2)�Œu��������������ɁA���̗\��ꂪ�c���Ă��Ȃ��ꍇ <BR>
     * �u���������������ԋp����B <BR>
     * <BR>
     * <BR>
     * EX1) �i�،���ЃR�[�h="40"�A���X�R�[�h="123"�̏ꍇ�j <BR>
     * �E����.�o�^�l�F"AAAAA_%%INSTITUTION_CODE%%_AAAAA"�̏ꍇ <BR>
     * �ԋp����镶����F"AAAAA_40_AAAAA" <BR>
     * <BR>
     * �E����.�o�^�l�F <BR>
     * "BBBBB_%%INSTITUTION_CODE%%_BBBB_%%BRANCH_CODE%%BBB" <BR>
     * �ԋp����镶����F"BBBBB_40_BBBB_123BBB" <BR>
     * <BR>
     * EX2) �u�������s��Ȃ��\���̏ꍇ <BR>
     * ����.�o�^�l�F <BR>
     * "BBBBB_%%INSTITUTION_CODE%%_BBBB_%%HSTR%%BBB" <BR>
     * �ԋp����镶����F"BBBBB_40_BBBB_%%HSTR%%BBB" <BR>
     * <BR>
     * EX3) ���͋敪�̏ꍇ <BR>
     * ����.�o�^�l�F"abcde%%INPUT_DIV_(40)%%" <BR>
     * �ԋp����镶����F"abcde40" <BR>
     * <BR>
     * (*) �T�[�r�X���p�N�����T�[�r�X.get���ݓ��t()�̖߂�l <BR>
     * [get���ݓ��t�ɓn������] <BR>
     * ���ݓ��t=this.���ݓ��t <BR>
     *
     * @@param l_strRegValue -
     *            �o�^�l
     * @@return String
     * @@roseuid 41B661560113
     */
    public String replaceReservedWord(String l_strRegValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " replaceReservedWord(String)";
        log.entering(STR_METHOD_NAME);

        //1) ����.�o�^�l��"%%�`%%"�ŋ�؂�ꂽ�����񂪑��݂��邩�ǂ����𔻒肵�A
        //���݂��Ȃ������ꍇ�A����.�o�^�l��ԋp����B
        if (!this.isRequiredFormat(l_strRegValue))
        {
            log.debug("����.�o�^�l��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return l_strRegValue;
        }

        String[] l_reservedWords = this.getReservedWords(l_strRegValue);
        StringBuffer l_sbReturnValue = new StringBuffer();

        String l_reservedWord = null;

        WEB3SrvRegiStartInfoService l_startInfoService = (WEB3SrvRegiStartInfoService)Services.getService(WEB3SrvRegiStartInfoService.class);
        WEB3SrvRegiRegistService l_registService = (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        WEB3SrvRegiStreamCommon l_srvRegiStreamCommon = new WEB3SrvRegiStreamCommon();
        WEB3GentradeBatoClientService l_genBatoClientService = (WEB3GentradeBatoClientService)Services.getService(
        	WEB3GentradeBatoClientService.class);
        WEB3SrvRegiServiceInfoManagement l_serviceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3GentradeAccountManager l_accManager = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

        int l_intReservedWordCnt = 0;
        int l_intRegValueLength = l_strRegValue.length();
        for (int i = 0; i < l_intRegValueLength; i++)
        {
            if (!l_strRegValue.startsWith(l_reservedWords[l_intReservedWordCnt]))
            {
                //�߂�l�ǉ�
                l_sbReturnValue.append(l_strRegValue.charAt(0));
                //�o�^�l���k
                l_strRegValue = l_strRegValue.substring(1, l_intRegValueLength-i);
            }
            else
            {
                //2) ����.�o�^�l�ɑ��݂��Ă����\���ɂ���āA�ȉ��̕�������{�B
                if (RESERVED_WORD_INSTITUTION_CODE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�،���ЃR�[�h�h�̏ꍇ");
                    l_reservedWord = this.institutionCode;
                }
                else if (RESERVED_WORD_BRANCH_CODE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F���X�R�[�h�h�̏ꍇ");
                    l_reservedWord = this.branchCode;
                }
                else if (RESERVED_WORD_MAIN_ACCOUNT_CODE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�ڋq�R�[�h�h�̏ꍇ");
                    l_reservedWord = this.mainAccountCode;
                }
                else if (RESERVED_WORD_PRODUCT_CODE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�����R�[�h�h�̏ꍇ");
                    l_reservedWord = this.productCode;
                }
                else if (RESERVED_WORD_ENCRYPTION_ACCOUNT_CODE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�Í����ڋq�R�[�h�h�̏ꍇ");
                    l_reservedWord = l_startInfoService.getCryptAccountCode(this.mainAccountCode);
                }
                else if (RESERVED_WORD_HASH_CALC_VALUE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�n�b�V���v�Z���ʁh�̏ꍇ");
                    l_reservedWord = l_startInfoService.createHashValue(
                        this.institutionCode, 
                        this.srvDiv, 
                        this.branchCode,
                        this.mainAccountCode, 
                        this.currentTimestamp, 
                        this.marketCode, 
                        this.productCode, 
                        this.type, 
                        this.digestKey,
                        this.ssidValue);
                }
                //�d�l�ύX NO_195 docId�폜
                else if (RESERVED_WORD_TOKEN.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���FToken�h�̏ꍇ");
                    l_reservedWord = this.token;
                }
                else if (RESERVED_WORD_ORDER_CHANEL.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�����`���l���h�̏ꍇ");
                    l_reservedWord = this.orderChanel;
                }
                else if (RESERVED_WORD_TRADER.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F���ҁh�̏ꍇ");
                    l_reservedWord = this.traderCode;
                }
                else if (RESERVED_WORD_MARGIN_ACCOUNT_DIV.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�M�p�����敪�h�̏ꍇ");
                    l_reservedWord = this.marginAccountDiv;
                }
                else if (RESERVED_WORD_FUOP_OSE_ACCOUNT_DIV.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�敨OP�����敪�i��؁j");
                    l_reservedWord = this.futureOPAccountDiv;
                }
                else if (RESERVED_WORD_ACCOUNT_NAME.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�ڋq���h�̏ꍇ");
                    l_reservedWord = l_accManager.getMainAccount(this.institutionCode, this.branchCode, this.mainAccountCode).getDisplayAccountName();
                }
                else if (RESERVED_WORD_YYYYMMDD.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�N�����iYYYYMMDD�j�h�̏ꍇ");
                    l_reservedWord = WEB3DateUtility.formatDate(this.currentTimestamp, "yyyyMMdd");
                }
                else if (RESERVED_WORD_YYYYMMDDHHMM.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�N�����iYYYY-MM-DD-HH-MM�j�h�̏ꍇ");
                    l_reservedWord = WEB3DateUtility.formatDate(this.currentTimestamp, "yyyy-MM-dd-HH-mm");
                }
                else if (RESERVED_WORD_YYYYMMDDHHMM_2.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�N�����iYYYYMMDDHHMM�j�h�̏ꍇ");
                    l_reservedWord = WEB3DateUtility.formatDate(this.currentTimestamp, "yyyyMMddHHmm");
                }
                else if (RESERVED_WORD_APPLI_EXPIRE_DATE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�_������i�K�p�I�����j�h�̏ꍇ");
                    WEB3GentradeSrvRegiApplication l_application = l_registService.getServiceRegist(this.institutionCode, this.branchCode, this.srvDiv, this.mainAccountCode, WEB3SrvRegiCancelDivDef.USUAL_DEFAULT, WEB3EffectiveDivDef.EFFECTIVE, false);
                    Timestamp l_tsAppliEndDate = l_application == null ? null : l_application.getAppliEndDate();
                    l_reservedWord = l_tsAppliEndDate == null ? null : WEB3DateUtility.formatDate(l_tsAppliEndDate, "yyyyMMdd");
                }
                else if (RESERVED_WORD_HASH_ELEMENT_1.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�n�b�V���v�Z�v�f�P�h�̏ꍇ");
                    WEB3SrvRegiServiceUseKey[] l_serviceUseKey = l_serviceInfoManagement.getSrvMaster(this.institutionCode, this.srvDiv, false).getHashList();

                    if (l_serviceUseKey != null && l_serviceUseKey.length >= 1)
                    {
                        l_reservedWord = l_serviceUseKey[0].getSrvUseKey();
                    }
                    else
                    {
                        l_reservedWord = null;
                    }
                }
                else if (RESERVED_WORD_HASH_ELEMENT_2.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�n�b�V���v�Z�v�f�Q�h�̏ꍇ");
                    WEB3SrvRegiServiceUseKey[] l_serviceUseKey = l_serviceInfoManagement.getSrvMaster(this.institutionCode, this.srvDiv, false).getHashList();

                    if (l_serviceUseKey != null && l_serviceUseKey.length >= 2)
                    {
                        l_reservedWord = l_serviceUseKey[1].getSrvUseKey();
                    }
                    else
                    {
                        l_reservedWord = null;
                    }
                }
                else if (RESERVED_WORD_TRADER_NAME.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F���Җ��h�̏ꍇ");

                    if (this.traderCode != null)
                    {
                        try
                        {
                            Institution l_institution = l_accManager.getInstitution(this.institutionCode);

                            l_reservedWord = ((TraderRow)GtlUtils.getFinObjectManager().getTrader(l_institution, this.traderCode, this.branchCode).getDataSourceObject()).getFamilyName();
                        }
                        catch (NotFoundException l_ex)
                        {
                            log.error(this.getClass().getName() + STR_METHOD_NAME);

                            log.exiting(STR_METHOD_NAME);

                            //��O���X���[����
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName() + STR_METHOD_NAME);
                        }
                    }
                    else
                    {
                        l_reservedWord = null;
                    }
                }
                else if (l_reservedWords[l_intReservedWordCnt].startsWith(RESERVED_WORD_INPUT_DIV) && l_reservedWords[l_intReservedWordCnt].endsWith(RESERVED_WORD_INPUT_DIV_END))
                {
                    log.debug("�h�\���F���͋敪�h�̏ꍇ");

                    if (this.traderCode == null)
                    {
                        l_reservedWord = l_reservedWords[l_intReservedWordCnt].substring(RESERVED_WORD_INPUT_DIV.length(), l_reservedWords[l_intReservedWordCnt].length() - RESERVED_WORD_INPUT_DIV_END.length());
                    }
                    else
                    {
                        l_reservedWord = "1";
                    }
                }
                else if(RESERVED_WORD_HASH_ACCOUNT_ID.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�n�b�V�������ꂽ�ڋqID�h�̏ꍇ");
                    l_reservedWord = l_startInfoService.createAccountCodeHashValue(this.hashCalHowTo, this.institutionCode, this.branchCode, this.mainAccountCode);
                }
                else if(RESERVED_WORD_MARKET_CODE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�s��R�[�h�h�̏ꍇ");
                    l_reservedWord = this.marketCode;
                }
                else if(RESERVED_WORD_TYPE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�^�C�v�h�̏ꍇ");
                    l_reservedWord = this.type;
                }
                else if(RESERVED_WORD_SSID_VALUE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���FSSID�l�h�̏ꍇ");
                    l_reservedWord = this.ssidValue;
                }
                else if (RESERVED_WORD_ENCRYPTION_MF_ASSET.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�h�\���F�Í����ۗL�������h�̏ꍇ");
                    l_reservedWord = l_startInfoService.getEncryptionMfAsset(this.institutionCode, this.branchCode, this.mainAccountCode);
                }
                else if (RESERVED_WORD_HSTR.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_FUNDTYPE.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_FUNDCODE.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_DELYEAR.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_DELMONTH.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_PUTCALL.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_STRIKEPRC.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_TRADETYPE.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_BUYSELLFLAG.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_STKEXCODE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�i��u���j�̗\���̏ꍇ");
                    l_reservedWord = l_reservedWords[l_intReservedWordCnt];
                }
                else if (RESERVED_YEAR_MONTH_DAY.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�\���F�N�����iYYYYMMDDHHMISS�j�̏ꍇ");
                    //this.�_�C�W�F�X�g�L�[�̃L�[1(����������b)
                    l_reservedWord = this.digestKey.getKey1();
                }
                else if (RESERVED_GUID.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("�\���FGUID�̏ꍇ");
                    //this.�_�C�W�F�X�g�L�[�̃L�[3(GUID)
                    l_reservedWord = this.digestKey.getKey3();
                }
                else if (ID.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //���h�\���FID�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B
                    //�|this.ID
                    l_reservedWord = this.id;
                }
                else if (PASS.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //���h�\���FPASS�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B
                    //�|this.PASS
                    l_reservedWord = this.pass;
                }
                else if (RESERVED_BOND_BALANCE_LIST.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //���h�\���F���c�����X�g�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B
                    //�@@�|�T�[�r�X���p���A�g����.get���c�����X�g()�̖߂�l���Z�b�g����B  
                	//[get���c�����X�g()�ɓn������]
                	//�@@�⏕����:this.�⏕����
                	//���⏕������null�̏ꍇ�̓G���[�Ƃ���B
                	if (this.subAccount == null)
                	{
                        log.debug("�⏕���������w��ł��B");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03091,
                            this.getClass().getName() + STR_METHOD_NAME);
                	}
                	else
                	{
                		l_reservedWord = l_srvRegiStreamCommon.getBondBalanceList(this.subAccount);
                	}

                }
                else if (RESERVED_TRADINGPOWER_BALANCE_LIST.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                	//���h�\���F�]�͎c�����X�g�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B
                	//�@@�|�T�[�r�X���p���A�g����.get�]�͎c�����X�g()�̖߂�l���Z�b�g����B
                	//[get�]�͎c�����X�g()�ɓn������]
                	//�@@�⏕�����Fthis.�⏕����
                	//���⏕������null�̏ꍇ�̓G���[�Ƃ���B
                  	if (this.subAccount == null)
                	{
                        log.debug("�⏕���������w��ł��B");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03091,
                            this.getClass().getName() + STR_METHOD_NAME);
                	}
                	else
                	{
                		l_reservedWord = l_srvRegiStreamCommon.getTradingPowerBalanceList(
                            this.subAccount);
                	}
                }
                else if (RESERVED_STOCK_APPRAISAL_VALUE_INSPECTION.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                	//���h�\���F���Y�]���z�ꗗ�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B
                	//�@@�|���V���v���N�X�A�g�T�[�r�X.get���Y�]���z�ꗗ()�̖߂�l���Z�b�g����B
                	//[get���Y�]���z�ꗗ()�ɓn������]
                	//�@@�⏕�����Fthis.�⏕����
                	//���⏕������null�̏ꍇ�̓G���[�Ƃ���B
                  	if (this.subAccount == null)
                	{
                        log.debug("�⏕���������w��ł��B");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03091,
                            this.getClass().getName() + STR_METHOD_NAME);
                	}
                	else
                	{
                        WEB3TPBondSimplexCooperationService l_bondSimplexCooperationService =
                            (WEB3TPBondSimplexCooperationService)Services.getService(
                                WEB3TPBondSimplexCooperationService.class);
                        l_reservedWord =
                            l_bondSimplexCooperationService.getAssetList((WEB3GentradeSubAccount)this.subAccount);
                	}
                }
                else if (RESERVED_BOND_ENCRYPT_PASS.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                	//���h�\���F������p�Í����p�X���[�h�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B
                	//�@@�|�T�[�r�X���p���A�g����.get���A�g�p�Í����p�X���[�h()�̖߂�l���Z�b�g����B
                	//[get���A�g�p�Í����p�X���[�h()�ɓn������]
                	// �،���ЃR�[�h�F�@@this.�،���ЃR�[�h
                	//�@@���X�R�[�h�F�@@this.���X�R�[�h
                	//�@@�ڋq�R�[�h�F�@@this.�ڋq�R�[�h
                	//�@@�T�[�r�X�敪�Fthis.�T�[�r�X�敪
                	//�@@���ݓ��t�Fthis.���ݓ��t
                	//�@@���҃R�[�h�Fthis.���҃R�[�h
                	l_reservedWord = l_srvRegiStreamCommon.getBondOrgUsedCryptPassword(
                	    this.institutionCode,
                	    this.branchCode,
                	    this.mainAccountCode,
                	    this.srvDiv,
                	    this.currentTimestamp,
                	    this.traderCode);
                }
                else if (RESERVED_DENSHI_BATO_URL.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                	//���h�\���F�d�q��URL�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B
                	//�@@�|�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A�ڋq�I�u�W�F�N�g���擾
                	//[get�ڋq�ɓn������]
                	//�@@�،���ЃR�[�h�Fthis.�،���ЃR�[�h
                	//�@@���X�R�[�h�Fthis.���X�R�[�h
                	//�@@�����R�[�h�Fthis.�ڋq�R�[�h
                	//�@@�|�d�q���V�X�e���ڑ��T�[�r�XImpl.get�d�q���ڑ����()�̖߂�l���Z�b�g����B
                	//[get�d�q���ڑ����()�ɓn������]
                	//�ڋq�F�g���A�J�E���g�}�l�[�W��.get�ڋq()�̖߂�l
                	WEB3GentradeMainAccount l_mainAccount =
                	    l_accManager.getMainAccount(
                            this.institutionCode,
                    	    this.branchCode,
                    	    this.mainAccountCode);

                	l_reservedWord = l_genBatoClientService.getBatoConnectionInfo(l_mainAccount);
                	
                }
                else if (RESERVED_INFORMATION_SERVICE_LIST.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                	//���h�\���F���T�[�r�X���X�g�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B
                	//�|�T�[�r�X���p���A�g����.get���T�[�r�X���X�g()�̖߂�l���Z�b�g����B  
                	//[get���T�[�r�X���X�g�ɓn������]
                	//�،���ЃR�[�h�Fthis.�،���ЃR�[�h  
                	//���X�R�[�h�Fthis.���X�R�[�h
                	//�����R�[�h�Fthis.�ڋq�R�[�h  
                	//���ݓ��t�Fthis.���ݓ��t
                	l_reservedWord = l_srvRegiStreamCommon.getInfoServiceList(
                	    this.institutionCode,
                	    this.branchCode,
                	    this.mainAccountCode,
                	    this.currentTimestamp);
                }
                else if (RESERVED_RESIDENT.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                	//���h�\���F���Z�敪�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B
                	//�|�g���A�J�E���g�}�l�[�W��.get�ڋq()�̖߂�l���狏�Z�敪���擾���Z�b�g����B
                	//[get�ڋq�ɓn������]
                	//�،���ЃR�[�h�Fthis.�،���ЃR�[�h
                	//���X�R�[�h�Fthis.���X�R�[�h
                	//�����R�[�h�Fthis.�ڋq�R�[�h
                	WEB3GentradeMainAccount l_mainAccount =
                	    l_accManager.getMainAccount(
                            this.institutionCode,
                    	    this.branchCode,
                    	    this.mainAccountCode);
                	l_reservedWord = l_mainAccount.getMainAccountRow().getResident();
                }
                else if (RESERVED_OSE_LOGINID.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //���h�\���F���FX���O�C��ID�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B
                    //�|�T�[�r�X���Ǘ�.get�T�[�r�X�}�X�^�[().get�t���敪()���R�[������B
                    String l_strAdditionDiv = l_serviceInfoManagement.getSrvMaster(
                        this.institutionCode, this.srvDiv, false).getAdditionDiv();
                    //�|get�t���敪()�̖߂�l=null�̏ꍇ�A�u�t���敪��null�ł��B�v��O���X���[����B
                    if (l_strAdditionDiv == null)
                    {
                        log.debug("�t���敪��null�ł��B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03160,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "�t���敪��null�ł��B");
                    }
                    //�|�ȊO�̏ꍇ�Aget�t���敪()�̖߂�l+this.�ڋq�R�[�h.substring(0,6)���Z�b�g����B
                    else
                    {
                        l_reservedWord = l_strAdditionDiv;
                        if (this.mainAccountCode != null && this.mainAccountCode.length() > 5)
                        {
                            l_reservedWord = l_strAdditionDiv + this.mainAccountCode.substring(0, 6);
                        }
                    }
                }
                else if (RESERVED_OTHER_SRV_REGI_STATUS.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //���h�\���F���T�[�r�X�\���󋵁h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������
                    //�T�[�r�X���Ǘ�.get�T�[�r�X�}�X�^�[().get�t���敪(�T�[�r�X���p�L�[ID)���R�[������
                    //[get�t���敪�ɓn������]
                    //�@@�T�[�r�X���p�L�[ID : 2�i�Œ�l�j
                    WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
                        l_serviceInfoManagement.getSrvMaster(
                            this.institutionCode, this.srvDiv, false);
                    String l_strAdditionDiv = l_srvRegiServiceMaster.getAdditionDiv(2);

                    //�|get�t���敪()�̖߂�l=null�̏ꍇ�A�u�t���敪��null�ł��B�v��O���X���[����B
                    if (l_strAdditionDiv == null)
                    {
                        log.debug("�t���敪��null�ł��B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03160,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "�t���敪��null�ł��B");
                    }

                    //�|�T�[�r�X���p�\���o�^�T�[�r�X.get�T�[�r�X�\���o�^()���R�[������B
                    //[get�T�[�r�X�\���o�^�ɓn������]
                    //�@@�،���ЃR�[�h=this.�،���ЃR�[�h
                    //�@@���X�R�[�h=this.���X�R�[�h
                    //�@@�T�[�r�X�敪=get�t���敪()�̖߂�l
                    //�@@�����R�[�h=this.�ڋq�R�[�h
                    //�@@����敪="�ʏ�"
                    //�@@�L���敪="�L��"
                    //�@@is�s���b�N=false
                    WEB3GentradeSrvRegiApplication l_SrvRegiApplication =
                        l_registService.getServiceRegist(
                            this.institutionCode,
                            this.branchCode,
                            l_strAdditionDiv,
                            this.mainAccountCode,
                            WEB3SrvRegiCancelDivDef.USUAL_DEFAULT,
                            WEB3EffectiveDivDef.EFFECTIVE,
                            false);

                    if (l_SrvRegiApplication != null)
                    {
                        //�T�[�r�X�\���o�^�e�[�u���Ƀf�[�^�����݂���ꍇ�A�u1�v���Z�b�g����A
                        l_reservedWord = WEB3SrvRegiApplicationHasDataDef.HASDATA;
                    }
                    else
                    {
                        //���݂��Ȃ��ꍇ�A�u0�v���Z�b�g����B
                        l_reservedWord = WEB3SrvRegiApplicationHasDataDef.NODATA;
                    }
                }
                else if (RESERVED_EQUITY_TAXTYPE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //���h�\���F�����ŋ敪�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������
                    //�|�g���A�J�E���g�}�l�[�W��.get�ڋq()�̖߂�l����ŋ敪���擾���Z�b�g����B
                    //[get�ڋq�ɓn������]
                    //�@@�،���ЃR�[�h�Fthis.�،���ЃR�[�h
                    //�@@���X�R�[�h�Fthis.���X�R�[�h
                    //�@@�����R�[�h�Fthis.�ڋq�R�[�h
                    WEB3GentradeMainAccount l_mainAccount =
                        l_accManager.getMainAccount(
                            this.institutionCode,
                            this.branchCode,
                            this.mainAccountCode);
                    l_reservedWord =
                        l_mainAccount.getMainAccountRow().getTaxType().intValue() + "";
                }
                else if (RESERVED_EQUITY_TAXTYPE_N.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //���h�\���F�����ŋ敪�i���N�j�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������
                    //�|�g���A�J�E���g�}�l�[�W��.get�ڋq()�̖߂�l����ŋ敪�i���N�j���擾���Z�b�g����B
                    //[get�ڋq�ɓn������]
                    //�@@�،���ЃR�[�h�Fthis.�،���ЃR�[�h
                    //�@@���X�R�[�h�Fthis.���X�R�[�h
                    //�@@�����R�[�h�Fthis.�ڋq�R�[�h
                    WEB3GentradeMainAccount l_mainAccount =
                        l_accManager.getMainAccount(
                            this.institutionCode,
                            this.branchCode,
                            this.mainAccountCode);
                    l_reservedWord =
                        l_mainAccount.getMainAccountRow().getTaxTypeNext().intValue() + "";
                }
                else if (RESERVED_MARGIN_TAXTYPE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //���h�\���F�M�p�ŋ敪�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B
                    //�|�g���A�J�E���g�}�l�[�W��.get�ڋq()�̖߂�l����M�p����ŋ敪���擾���Z�b�g����B
                    //[get�ڋq�ɓn������]
                    //�@@�،���ЃR�[�h�Fthis.�،���ЃR�[�h
                    //�@@���X�R�[�h�Fthis.���X�R�[�h
                    //�@@�����R�[�h�Fthis.�ڋq�R�[�h
                    WEB3GentradeMainAccount l_mainAccount =
                        l_accManager.getMainAccount(
                            this.institutionCode,
                            this.branchCode,
                            this.mainAccountCode);
                    TaxTypeEnum l_marginTaxType =
                        l_mainAccount.getMainAccountRow().getMarginTaxType();
                    if (l_marginTaxType != null)
                    {
                        l_reservedWord = l_marginTaxType.intValue() + "";
                    }
                    else
                    {
                        l_reservedWord = null;
                    }
                }
                else if (RESERVED_MARGIN_TAXTYPE_N.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //���h�\���F�M�p�ŋ敪�i���N�j�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B
                    //�|�g���A�J�E���g�}�l�[�W��.get�ڋq()�̖߂�l����M�p����ŋ敪�i���N�j���擾���Z�b�g����B
                    //[get�ڋq�ɓn������]
                    //�@@�،���ЃR�[�h�Fthis.�،���ЃR�[�h
                    //�@@���X�R�[�h�Fthis.���X�R�[�h
                    //�@@�����R�[�h�Fthis.�ڋq�R�[�h
                    WEB3GentradeMainAccount l_mainAccount =
                        l_accManager.getMainAccount(
                            this.institutionCode,
                            this.branchCode,
                            this.mainAccountCode);
                    TaxTypeEnum l_marginTaxType =
                        l_mainAccount.getMainAccountRow().getMarginTaxTypeNext();
                    if (l_marginTaxType != null)
                    {
                        l_reservedWord = l_marginTaxType.intValue() + "";
                    }
                    else
                    {
                        l_reservedWord = null;
                    }
                }
                else if (RESERVED_CD_KEY.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //���h�\���FCD�L�[�h�̏ꍇ�A�\�����ȉ��̒l�ɒu��������B
                    //�|�T�[�r�X���p�N�����T�[�r�X.getCD�L�[()�̖߂�l���擾���Z�b�g����B
                    //[getCD�L�[�ɓn������]
                    //�@@���X�R�[�h�Fthis.���X�R�[�h
                    //�@@�����R�[�h�Fthis.�ڋq�R�[�h
                    //�@@�T�[�r�X�敪�Fthis.�T�[�r�X�敪
                    //  �،���ЃR�[�h�Fthis.�،���ЃR�[�h
                    l_reservedWord = l_startInfoService.getCDKey(
                        this.branchCode,
                        this.mainAccountCode,
                        this.srvDiv,
                        this.institutionCode);
                }
                else
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    log.debug("�y�\���Y���Ȃ��z: "+l_reservedWords[l_intReservedWordCnt]);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                //�폜�������u��������̗\���
                //l_strRegValue = l_strRegValue.replaceFirst(l_reservedWords[l_intReservedWordCnt], "");
                l_strRegValue = replace(l_strRegValue, l_reservedWords[l_intReservedWordCnt], "");
                //�\���ǉ�����߂��
                if (l_reservedWord != null)
                {
                    l_sbReturnValue.append(l_reservedWord);
                }
                i += l_reservedWords[l_intReservedWordCnt].length() - 1;
                l_intReservedWordCnt += 1;

                //�\���u��������Ȃ�
                if (l_intReservedWordCnt >= l_reservedWords.length)
                {
                    if (l_reservedWord != null)
                    {
                        l_sbReturnValue.append(l_strRegValue);
                    }
                    break;
                }
            }
        }


        log.exiting(STR_METHOD_NAME);
        return l_sbReturnValue.toString();
    }

    /**
     * �����̒���"%%�`%%"�ŋ�؂̊l��
     * @@param l_strUrl
     * @@return String[]
     */
    private String[] getReservedWords(String l_strUrl)
    {
        if (WEB3StringTypeUtility.isEmptyOrBlank(l_strUrl))
        {
            return null;
        }

        final String l_strDoublePercents = "%%";
        List l_lisReservedWords = new ArrayList();

        int l_intUrlLength = l_strUrl.length();

        for (int i = 0; i < l_intUrlLength - 1; i++)
        {
            if (l_strDoublePercents.equals(l_strUrl.substring(i, i + 2)))
            {
                int l_intHead = 0;
                int l_intTail = 0;

                for (int j = i + 2; j < l_intUrlLength - 1; j++)
                {
                    l_intHead = i;
                    if (l_strDoublePercents.equals(l_strUrl.substring(j, j + 2)))
                    {
                        l_intTail = j + 2;
                        i = j + 1;
                        l_lisReservedWords.add(l_strUrl.substring(l_intHead, l_intTail));
                        break;
                    }
                }
            }
        }

        String[] l_strReservedWords = new String[l_lisReservedWords.size()];
        l_lisReservedWords.toArray(l_strReservedWords);

        return l_strReservedWords;
    }

    /**
     * �����̒���"%%�`%%"�ŋ�؂�ꂽ�����񂪑��݂��邩�ǂ����𔻒肵
     * @@param l_strUrl
     * @@return boolean
     */
    private boolean isRequiredFormat(String l_strUrl)
    {
        if (WEB3StringTypeUtility.isEmptyOrBlank(l_strUrl))
        {
            return false;
        }

        final String l_strDoublePercents = "%%";

        int l_intUrlLength = l_strUrl.length();

        for (int i = 0; i < l_intUrlLength - 1; i++)
        {
            if (l_strDoublePercents.equals(l_strUrl.substring(i, i + 2)))
            {
                for (int j = i + 2; j < l_intUrlLength - 1; j++)
                {
                    if (l_strDoublePercents.equals(l_strUrl.substring(j, j + 2)))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * �������u�����܂��B<BR>
     * ������u���Ώۂ̕�����̒��ɂ��錟���������S�āA�u��������ɒu��������<BR>
     * �������ԋp���܂��B
     *
     * @@param l_str ������u���Ώۂ̕�����
     * @@param l_strSearch ����������
     * @@param l_strReplace �u��������
     * @@return ������u����̕������Ԃ��B
     */
    public static String replace(String l_str, String l_strSearch, String l_strReplace)
    {

        if (l_strReplace == null)
        {
            l_strReplace = "";
        }

        for (int i = l_str.indexOf(l_strSearch); i != -1; i = l_str.indexOf(l_strSearch, i + l_strReplace.length()))
        {
            l_str = l_str.substring(0, i) + l_strReplace + l_str.substring(i + l_strSearch.length());
        }
        return l_str;
    }
}@
