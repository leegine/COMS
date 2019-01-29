head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualApplyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  ���M��W�������̓��X�|���X�N���X(WEB3MutualApplyInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/26 ���� (���u) �V�K�쐬
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;

/**
 * ���M��W�������̓��X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0   
 */

public class WEB3MutualApplyInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_apply_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200509261532L;
    
    /**
     * (���t�\�z)<BR>  
     *  ���t�\�z<BR>  
     */
    public String tradingPower;
    
    /**
     * (�����R�[�h)<BR>  
     * ���M�����R�[�h <BR>  
     *�i��ʂł͔�\���j <BR>     
     */
    public String mutualProductCode;
    
    /**
     * (������)<BR>  
     *  ������<BR>  
     */
    public String mutualProductName;
    
    /**
     * (��W���z�ʉ݃R�[�h)<BR>  
     *  �W���z�ʉ݃R�[�h <BR>  
     *  A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$ <BR>
     *  A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr <BR>
     *  F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS <BR>
     *  M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~<BR>
     *  T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR <BR> 
     */
    public String constantValueCurrencyCode;
    
    /**
     * (��W���z)<BR>  
     *  ��W���z<BR>  
     */
    public String applyConstantValue;
    
    /**
     * (�����敪�ꗗ)<BR>  
     *  �����敪�ꗗ<BR>
     *  0:��ʁ@@1:����<BR> 
     */
    public String[] taxTypeList;
    
    /**
     * (�w����@@�ꗗ)<BR>  
     *  �w����@@�ꗗ<BR>  
     *  3:���z�@@4:����<BR>
     */
    public String[] specifyDivList;
    
    /**
     * (��W���P�ʌ���)<BR>  
     *  ��W���P�ʌ���<BR>  
     */
    public String applyUnitQty;
    
    /**
     * (��W���Œ����)<BR>  
     *  ��W���Œ����<BR>  
     */
    public String applyMinQty;
    
    /**
     * (��W���P�ʋ��z)<BR>  
     *  ��W���P�ʋ��z<BR>  
     */
    public String applyUnitAmt;
    
    /**
     * (��W���Œ���z)<BR>  
     *  ��W���Œ���z<BR>  
     */
    public String applyMinAmt;
    
    /**
     * (���ϕ��@@�ꗗ)<BR>  
     *  ���ϕ��@@�ꗗ<BR> 
     *  1:�~�݁@@2:�O��<BR>  
     */
    public String[] settleDivList;
    
    /**
     * (������)<BR>  
     *  ������<BR>  
     */
    public Date orderBizDate;
    
    /**
     * (����)<BR>  
     *  ����<BR>  
     */
    public Date executionTimestamp;
    
    /**
     * (��n��)<BR>  
     *  ��n��<BR>  
     */
    public Date deliveryDate;
    
    /**
     * (�ژ_�����{���`�F�b�N����)<BR>  
     *  �ژ_�����{���`�F�b�N����<BR>  
     */
    public WEB3GentradeProspectusResult prospectusResult;
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3MutualApplyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
