head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M������m�F���X�|���X�N���X(WEB3MutualCancelConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
Revesion History : 2004/08/24 ���� (���u) ���r���[
Revesion History : 2006/10/16 ���� �d�l�ύX�E���f��506
Revesion History : 2007/02/09 �����F (���u) �d�l�ύX�E���f��540
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �����M������m�F���X�|���X�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualCancelConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_cancel_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L; 
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3MutualCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
    
    /**
     * ������
     */
    public String mutualProductName;
    
    /**
     * �v�Z����z�ʉ݃R�[�h<BR>
     * <BR>
     * �u�����N:�~�@@�u�����N�~�Q:�~<BR>
     * A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$<BR>
     * A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr<BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS<BR>
     * M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~<BR>
     * T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR<BR>
     * <BR>
     */
    public String constantValueCurrencyCode;
    
    /**
     * (�v�Z����z)<BR>
     * �v�Z�Ɏg�p��������z<BR>
     */
    public String constantValue;
    
    /**
     * ����z�K�p��
     */
    public Date constantValueAppDate;
    
    /**
     * �����敪<BR>
     * <BR>
     * 0:��ʁ@@1:����  2:���̑�<BR>
     */
    public String taxType;
    
    /**
     * �����敪(���M)<BR>
     * <BR>
     * 1:���t�@@2:���@@3:�抷�@@5:��W<BR>
     */
    public String mutualDealingType;
    
    /**
     * �������@@<BR>
     * <BR>
     * 0:��񐿋��@@1:���搿��<BR>
     * <BR>
     * (���t�����̏ꍇ�Anull)<BR>
     */
    public String sellBuyDiv;
    
    /**
     * �w����@@<BR>
     * <BR>
     * 2:�S���@@3:���z�@@4:����<BR>
     */
    public String specifyDiv;
    
    /**
     * �������ʋ敪<BR>
     * <BR>
     * 0:����<BR>
     * �u�����N:�~�@@�u�����N�~�Q:�~<BR>
     * A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$<BR>
     * A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr<BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS<BR>
     * M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~<BR>
     * T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR<BR>
     * <BR>
     */
    public String mutualOrderQuantityType;
    
    /**
     * ��������
     */
    public String mutualOrderQuantity;
    
    /**
     * �T�Z��n����ʉ݃R�[�h<BR>
     * <BR>
     * �u�����N:�~�@@�u�����N�~�Q:�~<BR>
     * A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$<BR>
     * A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr<BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS<BR>
     * M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~<BR>
     * T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR<BR>
     * <BR>
     * (����̑ΏۂƂȂ钍����"�抷"�̏ꍇ�Anull���ݒ肳���)<BR>
     */
    public String estimatedPriceCurrencyCode;
    
    /**
     * �T�Z��n���<BR>
     * <BR>
     * (����̑ΏۂƂȂ钍����"�抷"�̏ꍇ�Anull���ݒ肳���)<BR>
     */
    public String estimatedPrice;
    
    /**
     * �T�Z��������<BR>
     * <BR>
     * (����̑ΏۂƂȂ钍����"�抷"�̏ꍇ�Anull���ݒ肳���)<BR>
     */
    public String estimatedQty;
    
    /**
     * �������i�抷��j<BR>
     * <BR>
     * (����̑ΏۂƂȂ钍����"�抷"�ȊO�̏ꍇ�Anull���ݒ肳���)<BR>
     */
    public String switchingProductName;
    
    /**
     * �v�Z����z�ʉ݃R�[�h�i�抷��j<BR>
     * <BR>
     * �u�����N:�~�@@�u�����N�~�Q:�~<BR>
     * A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$<BR>
     * A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr<BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS<BR>
     * M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~<BR>
     * T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR<BR>
     * <BR>
     * (����̑ΏۂƂȂ钍����"�抷"�ȊO�̏ꍇ�Anull���ݒ肳���)<BR>
     */
    public String switchingConstantValueCurrencyCode;
    
    /**
     * �v�Z����z�i�抷��j<BR>
     * <BR>
     * (����̑ΏۂƂȂ钍����"�抷"�ȊO�̏ꍇ�Anull���ݒ肳���)<BR>
     */
    public String switchingConstantValue;
    
    /**
     * �����敪�i�抷��j<BR>
     * <BR>
     * 0:��ʁ@@1:����<BR>
     * <BR>
     * (����̑ΏۂƂȂ钍����"�抷"�ȊO�̏ꍇ�Anull���ݒ肳���)<BR>
     */
    public String switchingTaxType;
    
    /**
     * �T�Z���t�����i�抷��j<BR>
     * <BR>
     * (����̑ΏۂƂȂ钍����"�抷"�ȊO�̏ꍇ�Anull���ݒ肳���)<BR>
     */
    public String switchingEstimatedQty;
    
    /**
     * ���ϕ��@@<BR>
     * <BR>
     * 1:�~�݁@@2:�O��<BR>
     * <BR>
     * (����̑ΏۂƂȂ钍����"�抷"�̏ꍇ�Anull���ݒ肳���)<BR>
     */
    public String settleDiv;
    
    /**
     * ��n���@@<BR>
     * <BR>
     * 1:��s�U���݁@@2:�،���������<BR>
     * <BR>
     * (����̑ΏۂƂȂ钍����"�抷"�̏ꍇ�Anull���ݒ肳���)<BR>
     */
    public String deliveryDiv;
    
    /**
     * ������
     */
    public Date orderBizDate;
    
    /**
     * ����
     */
    public Date executionTimestamp;
    
    /**
     * ��n��
     */
    public Date deliveryDate;
    
    /**
     * �m�F��������<BR>
     * <BR>
     * �������N�G�X�g�ő��M����l���i�[����B<BR>
     * �i��ʂł͔�\���j<BR>
     */
    public Date checkDate;
    
    /**
     * (���M����m�F���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40A9A8DF03D4
     */
    public WEB3MutualCancelConfirmResponse() 
    {
     
    }
}
@
