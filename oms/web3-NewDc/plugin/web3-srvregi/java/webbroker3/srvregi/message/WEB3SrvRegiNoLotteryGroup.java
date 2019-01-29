head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiNoLotteryGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p���I���T�[�r�X���׏��ꗗ�s(WEB3SrvRegiNoLotteryGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �A�C��(���u) �V�K�쐬
Revesion History : 2007/06/05 ���^�](���u) �d�l�ύX���f��No.248
*/

package webbroker3.srvregi.message;

import java.util.Date;

/**
 * (�T�[�r�X���p���I���T�[�r�X���׏��ꗗ�s)<BR>
 * �T�[�r�X���p���I���T�[�r�X���׏��ꗗ�s�f�[�^�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3SrvRegiNoLotteryGroup extends WEB3AdminSrvRegiDetailCommon 
{
   
    /**
     * (�T�[�r�X����URL)
     */
    public String explainURL;
    
    /**
     * (���p���ԗ������)
     */
    public WEB3SrvRegiChargeInfo[] chargeInfo;
    
    /**
     * (���p���ԒP�ʋ敪)<BR>
     * 1:�N�@@2:���@@3:��<BR>
     * �i���p���Ԃ������ꍇ�Anull�j<BR>
     */
    public String trialDiv;
    
    /**
     * (���p����)<BR>
     * �i���p���Ԃ������ꍇ�Anull�j<BR>
     */
    public String trialPeriod;
    
    /**
     * (�o�^��)
     */
    public Date registDate;
    
    /**
     * (���p����)
     */
    public Date useLimitDate;
    
    /**
     * (�d�q���ݒ�敪)<BR>
     * true:�ݒ�L�@@false:�ݒ薳<BR>
     */
    public boolean elePigeonDiv;
    
    /**
     * (�\���\�敪)<BR>
     * true:�\�@@false:�s��<BR>
     */
    public boolean applyAbleDiv;
    
    /**
     * (�p���\���\�敪)<BR>
     * true:�\�@@false:�s��<BR>
     */
    public boolean continuationDiv;
    
    /**
     * (�T�[�r�X���p�\�敪)<BR>
     * true:�\�@@false:�s��<BR>
     */
    public boolean useAbleDiv;

    /**
     * (�����\���\�敪)<BR>
     * true:�\�@@false:�s��<BR>
     */
    public boolean noChargeAbleDiv;

    /**
     * (�\�������敪)<BR>
     * 1:�����ΏہA2:�\���s��<BR>
     */
    public String applyAttributeDiv;

    /**
     * (�\����������From)<BR>
     * �\����������From(YYYYMMDD)<BR>
     */
    public Date applyAttributePeriodFrom;

    /**
     * (�\����������To)<BR>
     * �\����������To(YYYYMMDD)<BR>
     */
    public Date applyAttributePeriodTo;

    /**
     * (���������\���敪)<BR>
     * null or '0' �F�ʏ�\���@@'1'�F���������\��<BR>
     */
    public String freeAttributeApplyDiv;

    /**
     * (�T�[�r�X���p���I���T�[�r�X���׏��ꗗ�s)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40EE320300F0
     */
    public WEB3SrvRegiNoLotteryGroup() 
    {
     
    }
}
@
