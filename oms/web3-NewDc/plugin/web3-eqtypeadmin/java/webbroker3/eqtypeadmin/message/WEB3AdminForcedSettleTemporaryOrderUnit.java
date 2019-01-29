head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleTemporaryOrderUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ϒ����Ɖ���(WEB3AdminForcedSettletemporaryOrderUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/24 ��іQ (���u) �V�K�쐬
Revesion History : 2007/07/24 �����q (���u) �V�K�쐬�@@�d�l�ύX���f��No.159
Revesion History : 2007/08/27 �đo�g (���u) �d�l�ύX���f��No.163
*/

package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�������ϒ����Ɖ���)<BR>
 * �������ϒ����Ɖ���N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminForcedSettleTemporaryOrderUnit extends Message
{
    
    /**
     * ����ID<BR>
     */
    public String id;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;
    
    /**
     * (�ڋq��)<BR>
     * �ڋq��<BR>
     */
    public String accountName;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F�@@���<BR>
     * 1�F�@@����<BR>
     */
    public String taxType;
    
    /**
     * (���敪)<BR>
     * ���敪<BR>
     * <BR>
     * 1�F�@@����<BR>
     * 2�F�@@����<BR>
     */
    public String contractType;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public Date openDate;
    
    /**
     * (���ϊ���)<BR>
     * ���ϊ���<BR>
     */
    public Date closeDate;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String contractQuantity;
    
    /**
     * (���P��)<BR>
     * ���P��<BR>
     */
    public String contractPrice;
    
    /**
     * (�����)<BR>
     * �����<BR>
     */
    public String contractExecPrice;

    /**
     * (�ۏ؋��a����)<BR>
     * �ۏ؋��a����<BR>
     */
    public String marginCollateralRate;

    /**
     * (�Ǐؔ�����)<BR>
     * �Ǐؔ�����<BR>
     */
    public Date additionalOccurredDate;

    /**
     * (�Ǐ،o�ߓ���)<BR>
     * �Ǐ،o�ߓ���<BR>
     */
    public String additionalElapsedDays;

    /**
     * (�ٍϋ敪)<BR>
     * �ٍϋ敪<BR>
     * <BR>
     * 1�F�@@���x�M�p<BR>
     * 2�F�@@��ʐM�p<BR>
     */
    public String repaymentDiv;
    
    /**
     * (�ٍϊ����l)<BR>
     * �ٍϊ����l<BR>
     */
    public String repaymentTimeLimit;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public String orderQuantity;
    
    /**
     * (�����P���敪)<BR>
     * �����P���敪<BR>
     * <BR>
     * 0�F�@@���s<BR>
     * 1�F�@@�w�l<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     * �����P��<BR>
     */
    public String orderPrice;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date orderBizDate;
    
    /**
     * (�쐬����)<BR>
     * �쐬����<BR>
     */
    public Date createDate;
    
    /**
     * (�i��j���F����)<BR>
     * �i��j���F����<BR>
     */
    public Date approveDate;
    
    /**
     * (���F���)<BR>
     * ���F���<BR>
     * <BR>
     * 0�F�@@�����F<BR>
     * 1�F�@@���F��<BR>
     * 2�F�@@�񏳔F<BR>
     * 9�F�@@�G���[<BR>
     */
    public String approveState;
    
    /**
     * (���F�҃R�[�h)<BR>
     * ���F�҃R�[�h<BR>
     */
    public String checker;
    
    /**
     * (�����G���[���R�R�[�h)<BR>
     * �����G���[���R�R�[�h<BR>
     * <BR>
     * 0005�F�@@�����c���s���G���[<BR>
     * 0006�F�@@������~�����G���[<BR>
     * 0016�F�@@���ϊ��������σG���[<BR>
     * 0017�F�@@�����E���n�����o�^�σG���[<BR>
     * 9001�F�@@���̑��G���[<BR>
     */
    public String errorReason;
    
    /**
     * (��]���z���߃t���O)<BR>
     * ��]���z���߃t���O<BR>
     * <BR>
     * false�F�@@���߂Ȃ�<BR>
     * true�F�@@��]���z�𒴉�<BR>
     */
    public boolean baseAssetOverFlag;

    /**
     * (�T�Z�]���z)<BR>
     * �T�Z�]���z<BR>
     */
    public String estimatedAsset;

    /**
     * (�������ϗ��R)<BR>
     * �������ϗ��R<BR>
     */
    public WEB3AdminForcedSettleReasonUnit forcedSettleReason;
    
    /**
     * @@roseuid 462CA4290341
     */
    public WEB3AdminForcedSettleTemporaryOrderUnit() 
    {
     
    }
}
@
