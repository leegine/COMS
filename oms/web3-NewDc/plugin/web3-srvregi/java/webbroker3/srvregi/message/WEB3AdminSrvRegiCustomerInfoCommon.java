head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerInfoCommon.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�ڋq�T�[�r�X���ꗗ���ʏ��(WEB3AdminSrvRegiCustomerInfoCommon.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ���o�� �V�K�쐬
*/

package webbroker3.srvregi.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�T�[�r�X���p�ڋq�T�[�r�X���ꗗ���ʏ��)<BR>
 * �T�[�r�X���p�ڋq�T�[�r�X���ꗗ���ʏ��f�[�^�N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiCustomerInfoCommon extends Message 
{
    
    /**
     * (�T�[�r�X�敪)
     */
    public String serviceDiv;
    
    /**
     * (�\���o�^ID)
     */
    public String applyRegId;
    
    /**
     * (�T�[�r�X����)
     */
    public String serviceName;
    
    /**
     * (�\�����I�敪)<BR>
     * 0:���p�@@1:�\���@@2:���I�^�{�\���@@3:���I�@@4:����@@5:�������I�@@6:�S��<BR>
     * �inull:���o�^�j<BR>
     */
    public String applyLotteryDiv;
    
    /**
     * (�K�p�J�n��)
     */
    public Date trialStartDate;
    
    /**
     * (�K�p�I����)
     */
    public Date trialEndDate;
    
    /**
     * (�o�^�敪)<BR>
     * 0:�L���@@1:����<BR>
     */
    public String registDiv;
    
    /**
     * (���p����)
     */
    public String chargeAmt;
    
    /**
     * (�����\���敪)<BR>
     * null:�\���ΏۊO <BR>
     * 0:�� <BR>
     * 1:�L<BR>
     */
    public String firstApplyDiv;
    
    /**
     * (�ŏI�X�V��)
     */
    public Date lastUpdateTime;
    
    /**
     * (�ŏI�X�V��)
     */
    public String lastUpdater;
    
    /**
     * (�T�[�r�X���p�ڋq�T�[�r�X���ꗗ���ʏ��)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40EE56420064
     */
    public WEB3AdminSrvRegiCustomerInfoCommon() 
    {
     
    }
}
@
