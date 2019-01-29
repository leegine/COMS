head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeTradingClendarContext.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����J�����_�R���e�L�X�g(WEB3GentradeTradingClendarContext.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/13 羐� (���u) �V�K�쐬
*/

package webbroker3.gentrade;

/**
 * (����J�����_�R���e�L�X�g)<BR>
 * ������Ԃ𗘗p����T�[�r�X�̃C���^�Z�v�^�����p����R���e�L�X�g�B<BR>
 * �iTradingCalendarContext�j<BR>
 * <BR>
 * �����������E��t���Ԃ��Q�Ƃ���T�[�r�X�́A�K�����Y�R���e�L�X�g<BR>
 * �̓��e���Z�b�g�i�܂��̓N���A�j����C���^�Z�v�^��p�ӂ���K�v������B<BR>
 * <BR>
 * [�C���^�Z�v�^�̎d�l]<BR>
 * �@@�|onCall( )�ɂē��Y�R���e�L�X�g�̓��e��ݒ�<BR>
 * �@@�|onReturn( )�ɂē��Y�R���e�L�X�g�̓��e���N���A<BR>
 */
public class WEB3GentradeTradingClendarContext
{
    /**
     * �،���ЃR�[�h<BR>
     */
    private String institutionCode;

    /**
     * ���X�R�[�h<BR>
     */
    private String branchCode;

    /**
     * �s��R�[�h<BR>
     * <BR>
     * ���M��"0"�B<BR>
     * �s����w�肵�Ȃ��ꍇ��null���Z�b�g����B<BR>
     */
    private String marketCode;

    /**
     * ��t���ԋ敪<BR>
     */
    private String tradingTimeType;

    /**
     * ������t���i<BR>
     * �i������t�X�e�C�^�X�e�[�u��.������t���i�j<BR>
     */
    private String orderAcceptProduct;

    /**
     * ������t�g�����U�N�V����<BR>
     * �i������t�X�e�C�^�X�e�[�u��.������t�g�����U�N�V�����j<BR>
     */
    private String orderAcceptTransaction;

    /**
     * �����R�[�h<BR>
     * <BR>
     * �敨OP�^���M�̂ݎg�p�B<BR>
     * �ȊO�� 0�FDEFAULT�B<BR>
     */
    private String productCode;

    /**
     * (�c�Ɠ��敪) <BR>
     */
    private String bizDateType;

    /**
     * (�s��g���K���s)<BR>
     */
    private String submitMarketTrigger;

    /**
     * (��t�\)<BR>
     */
    private String enableOrder;

    /**
     * (�������v�Z)<BR>
     */
    private String bizdateCalcParameter;

    /**
     * (�����~���i)<BR>
     */
    private String tradingStopProduct;

    /**
     * (�����~�g�����U�N�V����)<BR>
     */
    private String tradingStopTransaction;

    /**
     * @@roseuid 40A2018D02F3
     */
    public WEB3GentradeTradingClendarContext()
    {

    }

    /**
     * �،���ЃR�[�h���Z�b�g����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@roseuid 4014B5D703B3
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        this.institutionCode = l_strInstitutionCode;
    }

    /**
     * �،���ЃR�[�h���擾����B<BR>
     * @@return java.lang.String<BR>
     * @@roseuid 4014C12103A3
     */
    public String getInstitutionCode()
    {
        return this.institutionCode;
    }

    /**
     * ���X�R�[�h���Z�b�g����B<BR>
     * @@param l_strBranchCode - ���X�R�[�h<BR>
     * @@roseuid 4014C16C00B5
     */
    public void setBranchCode(String l_strBranchCode)
    {
        this.branchCode = l_strBranchCode;
    }

    /**
     * ���X�R�[�h���擾����B<BR>
     * @@return java.lang.String<BR>
     * @@roseuid 4014C16C00B7
     */
    public String getBranchCode()
    {
        return this.branchCode;
    }

    /**
     * �s��R�[�h���Z�b�g����B<BR>
     * @@param l_strMarketCode - �s��R�[�h<BR>
     * @@roseuid 4014C18C026B
     */
    public void setMarketCode(String l_strMarketCode)
    {
        this.marketCode = l_strMarketCode;
    }

    /**
     * �s��R�[�h���擾����B<BR>
     * @@return java.lang.String<BR>
     * @@roseuid 4014C18C026D
     */
    public String getMarketCode()
    {
        return this.marketCode;
    }

    /**
     * ��t���ԋ敪���Z�b�g����B<BR>
     * @@param l_strTradingTimeType - ��t���ԋ敪<BR>
     * @@roseuid 401615E303D7
     */
    public void setTradingTimeType(String l_strTradingTimeType)
    {
        this.tradingTimeType = l_strTradingTimeType;
    }

    /**
     * ��t���ԋ敪���擾����B<BR>
     * @@return java.lang.String<BR>
     * @@roseuid 401615E303E7
     */
    public String getTradingTimeType()
    {
        return this.tradingTimeType;
    }

    /**
     * ���ׂẴN���X�ϐ���null���Z�b�g���A�R���e�L�X�g���N���A����B<BR>
     * @@roseuid 401618DF034B
     */
    public void clear()
    {
        this.institutionCode = null;
        this.branchCode = null;
        this.marketCode = null;
        this.tradingTimeType = null;
        this.orderAcceptProduct = null;
        this.orderAcceptTransaction = null;
        this.productCode = null;
        this.bizDateType = null;
        this.submitMarketTrigger = null;
        this.enableOrder = null;
        this.bizdateCalcParameter = null;
        this.tradingStopProduct = null;
        this.tradingStopTransaction = null;

    }

    /**
     * ������t���i���擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 404D40C20301
     */
    public String getOrderAcceptProduct()
    {
        return this.orderAcceptProduct;
    }

    /**
     * ������t���i���Z�b�g����B<BR>
     * @@param l_strOrderAcceptProduct - ������t���i<BR>
     * @@roseuid 404D40CF00BF
     */
    public void setOrderAcceptProduct(String l_strOrderAcceptProduct)
    {
        this.orderAcceptProduct = l_strOrderAcceptProduct;
    }

    /**
     * ������t�g�����U�N�V�������擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 404D40D001B9
     */
    public String getOrderAcceptTransaction()
    {
        return this.orderAcceptTransaction;
    }

    /**
     * ������t�g�����U�N�V�������Z�b�g����B<BR>
     * @@param l_strOrderAcceptTransaction - ������t�g�����U�N�V����<BR>
     * @@roseuid 404D40D10265
     */
    public void setOrderAcceptTransaction(String l_strOrderAcceptTransaction)
    {
        this.orderAcceptTransaction = l_strOrderAcceptTransaction;
    }

    /**
     * �����R�[�h���Z�b�g����B<BR>
     * <BR>
     * �敨OP�^���M�̂ݎg�p�B<BR>
     * �ȊO�� 0�FDEWFAULT�B<BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * @@roseuid 40610C2D001E
     */
    public void setProductCode(String l_strProductCode)
    {
        this.productCode = l_strProductCode;
    }

    /**
     * �����R�[�h���擾����B<BR>
     * <BR>
     * �敨OP�^���M�̂ݎg�p�B<BR>
     * �ȊO�� 0�FDEWFAULT�B<BR>
     * @@return java.lang.String
     * @@roseuid 40610C2D0176
     */
    public String getProductCode()
    {
        return this.productCode;
    }

    /**
     * (set�c�Ɠ��敪)<BR>
     *<BR> 
     * �c�Ɠ��敪���Z�b�g����B<BR>
     * @@param l_bizDateType - �c�Ɠ��敪
     * @@roseuid 401615E303D7
     */
    public void setBizDateType(String l_bizDateType)
    {
        this.bizDateType = l_bizDateType;
    }

    /**
     * (get�c�Ɠ��敪)<BR>
     *<BR> 
     * �c�Ɠ��敪���擾����B<BR>
     * @@return String
     * @@roseuid 401615E303E7
     */
    public String getBizDateType()
    {
        return this.bizDateType;
    }

    /**
     * (set�s��g���K���s)<BR>
     *<BR> 
     * �s��g���K���s���Z�b�g����B<BR>
     * @@param l_submitMarketTrigger - �s��g���K���s<BR>
     * @@roseuid 401615E303D7
     */
    public void setSubmitMarketTrigger(String l_submitMarketTrigger)
    {
        this.submitMarketTrigger = l_submitMarketTrigger;
    }

    /**
     * (get�s��g���K���s)<BR>
     *<BR> 
     * �s��g���K���s���擾����B<BR>
     * @@return String
     * @@roseuid 401615E303E7
     */
    public String getSubmitMarketTrigger()
    {
        return this.submitMarketTrigger;
    }

    /**
     * (set�������v�Z)<BR>
     *<BR> 
     * �������v�Z���Z�b�g����B<BR>
     * @@param l_bizdateCalcParameter - �������v�Z<BR>
     * @@roseuid 401615E303D7
     */
    public void setBizdateCalcParameter(String l_bizdateCalcParameter)
    {
        this.bizdateCalcParameter = l_bizdateCalcParameter;
    }

    /**
     * (get�������v�Z)<BR>
     *<BR> 
     * �������v�Z���擾����B<BR>
     * @@return String
     * @@roseuid 401615E303E7
     */
    public String getBizdateCalcParameter()
    {
        return this.bizdateCalcParameter;
    }

    /**
     * (set��t�\)<BR>
     *<BR> 
     * ��t�\���Z�b�g����B<BR>
     * @@param l_enableOrder - ��t�\<BR>
     * @@roseuid 401615E303D7
     */
    public void setEnableOrder(String l_enableOrder)
    {
        this.enableOrder = l_enableOrder;
    }

    /**
     * (get��t�\)<BR>
     *<BR> 
     * ��t�\���擾����B<BR>
     * @@return String
     * @@roseuid 401615E303E7
     */
    public String getEnableOrder()
    {
        return this.enableOrder;
    }

    /**
     * (set�����~�g�����U�N�V����)<BR>
     *<BR> 
     * �����~�g�����U�N�V�������Z�b�g����B<BR>
     * @@param l_tradingStopTransaction - �����~�g�����U�N�V����
     * @@roseuid 401615E303D7
     */
    public void setTradingStopTransaction(String l_tradingStopTransaction)
    {
        this.tradingStopTransaction = l_tradingStopTransaction;
    }

    /**
     * (get�����~�g�����U�N�V����)<BR>
     *<BR> 
     * �����~�g�����U�N�V�������擾����B<BR>
     * @@return String
     * @@roseuid 401615E303E7
     */
    public String getTradingStopTransaction()
    {
        return this.tradingStopTransaction;
    }

    /**
     * (set�����~���i)<BR>
     *<BR> 
     * �����~���i���Z�b�g����B<BR>
     * @@param l_tradingStopProduct - �����~���i<BR>
     * @@roseuid 401615E303D7
     */
    public void setTradingStopProduct(String l_tradingStopProduct)
    {
        this.tradingStopProduct = l_tradingStopProduct;
    }

    /**
     * (get�����~���i)<BR>
     *<BR> 
     * �����~���i���擾����B<BR>
     * @@return String
     * @@roseuid 401615E303E7
     */
    public String getTradingStopProduct()
    {
        return this.tradingStopProduct;
    }
}
@
