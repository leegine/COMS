head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����������o�^���̓��X�|���X(WEB3AdminMutualConditionsInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ����  (���u) �V�K�쐬
Revesion History : 2004/08/23 ������ (���u) ���r���[ 
Revesion History : 2004/12/09 ������ (���u) �c�Ή�
Revesion History : 2005/10/18 ��O�� (���u) �t�B�f���e�B�Ή�
Revesion History : 2006/04/09 �����F (���u)�@@���f��548
*/
package webbroker3.mf.message;
import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �����M�����������o�^���̓��X�|���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AdminMutualConditionsInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_conditions_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131500L;    
    
    /**
     * ���M��������R�[�h<BR>
     */
    public String mutualAssocProductCode;
    
    /**
     * �ŏI�X�V�҃R�[�h<BR>
     */
    public String lastUpdaterCode;
    
    /**
     * �ŏI�X�V����<BR>
     */
    public Date lastUpdateTime;
    
    /**
     * �ݒ��<BR>
     * <BR>
     */
    public Date settingDate;
    
    /**
     * ���ғ�<BR>
     * <BR>
     */
    public Date refundDate;
    
    /**
     * �����֓�<BR>
     * <BR>
     */
    public Date removalOfNotSell;
    
    /**
     * �w����@@�ꗗ�i���t�j<BR>
     * <BR>
     * 0:�I���w��@@3:���z�w��@@4:�����w��<BR>
     */
    public String[] buySelectableList;

    /**
     * �w����@@�ꗗ�i���j<BR>
     * <BR>
     * 0:�I���w��@@3:���z�w��@@4:�����w��<BR>
     */
    public String[] sellSelectableList;

    /**
     * �w����@@�ꗗ�i�抷�j<BR>
     * <BR>
     * 0:�I���w��@@3:���z�w��@@4:�����w��<BR>
     */
    public String[] switchingSelectableList;
    
    /**
     * �w����@@�ꗗ�i��W�j<BR>
     * <BR>
     * 0:�I���w��@@3:���z�w��@@4:�����w��<BR>
     */
    public String[] applySelectableList;
    
    /**
     * ���ݓ�����̔�����<BR>
     */
    public Date curBizDate;
    
    /**
     * ���ݓ�����̗��c�Ɠ�<BR>
     */
    public Date nextBizDate;

    /**
     * (�O��MMF�t���O)<BR>
     * �O��MMF�t���O<BR> 
     * <BR>
     * true:�������O��MMF <BR>
     * false:�������O��MMF�łȂ�<BR>
     */
    public boolean frgnMmfFlag;

    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h <BR>
     * <BR>
     * A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$ <BR>
     * A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr <BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS <BR>
     * M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~ <BR>
     * T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR<BR>
     */
    public String currencyCode;

    /**
     * ���M���������o�^���ʏ��<BR>
     */
    public WEB3MutualProductConditionsCommonInfo mutualProductInfo;
    
    /**
     * (���M���������o�^���̓��X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40DF7B7B01AC
     */
    public WEB3AdminMutualConditionsInputResponse()
    {
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminMutualConditionsInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
