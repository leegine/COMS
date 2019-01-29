head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundNewOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �g�����M�V�K�������e(WEB3MutualFundNewOrderSpec)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/06 ���� (���u) �V�K�쐬
*/
package webbroker3.mf;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbmf.ordersubmitter.io.MutualFundNewOrderSpec;

/**
 * �g�����M�V�K�������e<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualFundNewOrderSpec extends MutualFundNewOrderSpec 
{
    /**
     * (�g�����M�V�K�������e)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �P�j�@@this()���R�[������B<BR>
     * �@@�mthis�ɓn���p�����^�n<BR>
     * �@@�@@�㗝���͎ҁF ����.�㗝���͎�<BR>
     * �@@�@@is���t�F ����.is���t<BR>
     * �@@�@@�񍆃R�[�h�F �h0�FDEFAULT�h<BR>
     * �@@�@@�����R�[�h�F ����.�����R�[�h<BR>
     * �@@�@@�s��R�[�h�F �h0�FDEFAULT�h<BR>
     * �@@�@@�������ʁF ����.��������<BR>
     * �@@�@@�������ʃ^�C�v�F ����.�������ʃ^�C�v<BR>
     * �@@�@@�ŋ敪�F ����.�ŋ敪<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * ����<BR>
     * @@param l_blnIsAcquired - (is���t)<BR>
     * ���t�̏ꍇ�� true ���A���̏ꍇ�� false ��ݒ肷��<BR>
     * @@param l_strMutualProductCode - �����R�[�h<BR>
     * @@param l_dblOrderQuantity - ��������<BR>
     * @@param l_orderQuantityType - �������ʃ^�C�v<BR>
     * @@param l_taxType - �ŋ敪<BR>
     * @@roseuid 40ADAD9003B4
     */
    public WEB3MutualFundNewOrderSpec(
        Trader l_trader,
        boolean l_blnIsAcquired,
        String l_strMutualProductCode,
        double l_dblOrderQuantity,
        QuantityTypeEnum l_orderQuantityType,
        TaxTypeEnum l_taxType) 
    {
        this(
            l_trader,
            l_blnIsAcquired, 
            "0", 
            l_strMutualProductCode,
            "0",
            l_dblOrderQuantity,
            l_orderQuantityType,
            l_taxType);
    }
    /**
     * (�g�����M�V�K�������e)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �P�j�@@super()���R�[������B<BR>
     * �@@�msuper�ɓn���p�����^�n<BR>
     * �@@�@@�㗝���͎ҁF ����.�㗝���͎�<BR>
     * �@@�@@is���t�F ����.is���t<BR>
     * �@@�@@�񍆃R�[�h�F ����.�񍆃R�[�h<BR>
     * �@@�@@�����R�[�h�F ����.�����R�[�h<BR>
     * �@@�@@�s��R�[�h�F ����.�s��R�[�h<BR>
     * �@@�@@�������ʁF ����.��������<BR>
     * �@@�@@�������ʃ^�C�v�F ����.�������ʃ^�C�v<BR>
     * �@@�@@�ŋ敪�F ����.�ŋ敪<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * ����<BR>
     * @@param l_blnIsAcquired - ���t�̏ꍇ�� true ���A���̏ꍇ�� false ��ݒ肷��<BR>
     * @@param l_strProductIssueCode - �񍆃R�[�h<BR>
     * @@param l_strMutualProductCode - �����R�[�h<BR>
     * @@param l_strMarketCode - �s��R�[�h<BR>
     * @@param l_dblOrderQuantity - ��������<BR>
     * @@param l_orderQuantityType - �������ʃ^�C�v<BR>
     * @@param l_taxType - �ŋ敪<BR>
     * @@roseuid 40ADAD9003A4
     */
    public WEB3MutualFundNewOrderSpec(
        Trader l_trader,
        boolean l_blnIsAcquired,
        String l_strProductIssueCode,
        String l_strMutualProductCode,
        String l_strMarketCode,
        double l_dblOrderQuantity,
        QuantityTypeEnum l_orderQuantityType,
        TaxTypeEnum l_taxType) 
    {
        super(
            l_trader,
            l_blnIsAcquired,
            l_strProductIssueCode,
            l_strMutualProductCode,
            l_strMarketCode,
            l_dblOrderQuantity,
            l_orderQuantityType,
            l_taxType);
    }
}
@
