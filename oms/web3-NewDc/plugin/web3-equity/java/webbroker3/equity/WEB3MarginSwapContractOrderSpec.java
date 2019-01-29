head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapContractOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  /**
                 :  (�M�p�������n�������e)<BR>
                 :  �M�p����E�������n�������e�̓��͂�\������B<BR>
                 :  �����}�l�[�W���ɓn���p�����^�ɂȂ�B<BR>(WEB3MarginSwapContractOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 ������ (���u) �V�K�쐬
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSwapContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�������n�������e�j�B<BR>
 * <BR>
 * �M�p����E�������n�������e�̓��͂�\������B<BR>
 * �����}�l�[�W���ɓn���p�����^�ɂȂ�B<BR>
 * <BR>
 * xTrade��EqTypeSwapContractOrderSpec���g�������N���X�B
 * @@version 1.0
 */
public class WEB3MarginSwapContractOrderSpec extends EqTypeSwapContractOrderSpec 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B<BR>
    */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3MarginSwapContractOrderSpec.class);

    
    /**
     * �R���X�g���N�^�B<BR>
     * @@param l_trader - (����)
     * @@param l_closeMarginContractEntry - (���ό����G���g��)
     * @@param l_taxType - (�ŋ敪)
     */
    protected WEB3MarginSwapContractOrderSpec(
        Trader l_trader,
        EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry,
        TaxTypeEnum l_taxType)
    {
        super(l_trader, l_closeMarginContractEntry, l_taxType);
    }

    /**
     * (���Ϗ����敪�B0<BR>
     * (0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������)
     */
    private String closingOrderType;
    
    /**
     * (�ŋ敪�i�������n�j)<BR>
     * ������^���n�������̐ŋ敪
     */
    private TaxTypeEnum swapTaxType;

    /**
     * (create�������n�������e)<BR>
     * �istatic���\�b�h�j<BR>
     * �M�p�������n�������e�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �葱���̓V�[�P���X�}�u�i�M�p�����jcreate�������n�������e�v���Q�ƁB<BR>
     * @@param l_trader - (����)<BR>
     * ���҃I�u�W�F�N�g
     * @@param l_closeMarginContractEntry - (���ό����G���g��)<BR>
     * ���ό����G���g���̔z��B
     * @@param l_strClosingOrderType - ���Ϗ����敪�B<BR>
     * (0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������)
     * @@param l_taxType - (�ŋ敪)<BR>
     * �����̐ŋ敪�B<BR>
     * <BR>
     * 0:���̑�,�@@1:���,�@@2:����,�@@3:������������򒥎�<BR>
     * �iTaxTypeEnum�ɂĒ�`�j<BR>
     * @@param l_swapTaxType - (�ŋ敪�i�������n�j)<BR>
     * ������^���n���̐ŋ敪�B<BR>
     * <BR>
     * 0:���̑�,�@@1:���,�@@2:����,�@@3:������������򒥎�<BR>
     * �iTaxTypeEnum�ɂĒ�`�j<BR>
     * @@return WEB3MarginSwapContractOrderSpec
     * @@roseuid 40BBD2F501A4
     */
    public static WEB3MarginSwapContractOrderSpec createSwapMarginOrderSpec(Trader l_trader, EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry, String l_strClosingOrderType, TaxTypeEnum l_taxType, TaxTypeEnum l_swapTaxType) 
    {
        String STR_METHOD_NAME="createSwapMarginOrderSpec(Trader,EqTypeSettleContractOrderEntry[],String,TaxTypeEnum,TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginSwapContractOrderSpec l_marginSwapContractOrderSpec=
            new WEB3MarginSwapContractOrderSpec(l_trader,l_closeMarginContractEntry,l_taxType);
            
        l_marginSwapContractOrderSpec.setClosingOrderType(l_strClosingOrderType);
        l_marginSwapContractOrderSpec.setSwapTaxType(l_swapTaxType);
        
        log.exiting(STR_METHOD_NAME);
        return  l_marginSwapContractOrderSpec;
    }
    
    /**
     * (set���Ϗ����敪)<BR>
     * ���Ϗ����敪���Z�b�g����B
     * @@param l_strClosingOrderType - ���Ϗ����敪�B
     * @@roseuid 40BD5C61025D
     */
    public void setClosingOrderType(String l_strClosingOrderType) 
    {
        this.closingOrderType = l_strClosingOrderType;
    }
    
    /**
     * (get���Ϗ����敪)<BR>
     * ���Ϗ����敪���擾����BClosingOrderType
     * @@return String
     * @@roseuid 40BD5C61026D
     */
    public String getClosingOrderType() 
    {
        return this.closingOrderType;
    }
    
    /**
     * (set�ŋ敪�i�������n�j)<BR>
     * �ŋ敪�i�������n�j���Z�b�g����B
     * @@param l_swapTaxType - �ŋ敪�i�������n�j�B
     * @@roseuid 40BD535B0328
     */
    public void setSwapTaxType(TaxTypeEnum l_swapTaxType) 
    {
        this.swapTaxType = l_swapTaxType;
    }
    
    /**
     * (get�ŋ敪�i�������n�j)<BR>
     * �ŋ敪�i�������n�j���擾����B
     * @@return TaxTypeEnum
     * @@roseuid 40BD535B032A
     */
    public TaxTypeEnum getSwapTaxType() 
    {
        return this.swapTaxType;
    }
}
@
