head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiLotteryStateGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�ڋq�\���󋵈ꗗ���I�L�T�[�r�X�ꗗ�s(WEB3AdminSrvRegiLotteryStateGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi.message;

import java.util.Date;


/**
 * (�T�[�r�X���p�ڋq�\���󋵈ꗗ���I�L�T�[�r�X�ꗗ�s)<BR>
 * �T�[�r�X���p�ڋq�\���󋵈ꗗ���I�L�T�[�r�X�ꗗ�s�f�[�^�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiLotteryStateGroup extends WEB3AdminSrvRegiCustomerInfoCommon 
{
    
    /**
     * (�\����)
     */
    public Date applyDate;
    
    /**
     * (�o�^�\�敪)<BR>
     * true:�\�@@false:�s��<BR>
     */
    public boolean registAbleDiv;
    
    /**
     * (�ύX�\�敪)<BR>
     * true:�\�@@false:�s��<BR>
     */
    public boolean changeAbleDiv;
    
    /**
     * (�T�[�r�X���p�ڋq�\���󋵈ꗗ���I�L�T�[�r�X�ꗗ�s)
     * @@roseuid 40EE55D00323
     */
    public WEB3AdminSrvRegiLotteryStateGroup() 
    {
     
    }
}
@
