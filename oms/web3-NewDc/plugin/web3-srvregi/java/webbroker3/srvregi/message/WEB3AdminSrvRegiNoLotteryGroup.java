head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiNoLotteryGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ғ��I���T�[�r�X���׏��ꗗ�s(WEB3AdminSrvRegiNoLotteryGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi.message;

/**
 * (�T�[�r�X���p�Ǘ��Ғ��I���T�[�r�X���׏��ꗗ�s)<BR>
 * �T�[�r�X���p�Ǘ��Ғ��I���T�[�r�X���׏��ꗗ�s�f�[�^�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiNoLotteryGroup extends WEB3AdminSrvRegiDetailCommon 
{

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
     * (�T�[�r�X���p�Ǘ��Ғ��I���T�[�r�X���׏��ꗗ�s)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F2206303D8
     */
    public WEB3AdminSrvRegiNoLotteryGroup() 
    {
     
    }
}
@
