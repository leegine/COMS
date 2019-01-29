head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ServiceImpState.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �T�[�r�X���{��Ԃ�xTrade�N���C�A���g�ɓn���ׂ̃��b�Z�[�W�N���X(WEB3ServiceImpState.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/13 �e�n(SRA)
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;

/**
 * (�T�[�r�X���{���)<BR>
 * �T�[�r�X���{��Ԃ�xTrade�N���C�A���g�ɓn���ׂ̃��b�Z�[�W�N���X<BR>
 * <BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3ServiceImpState extends Message
{

    /**
     * ������񃊃A���f�B���C�敪�i���A���j<BR>
     */
    public static final String QUICK_REAL = "07_Quick_Real";

    /**
     * ������񃊃A���f�B���C�敪�i�f�B���C�j<BR>
     */
    public static final String QUICK_DELAY = "07_Quick_Delay";

    /**
     * (������񃊃A���f�B���C�敪)<BR>
     * "07_Quick_Real" �F���A��<BR>
     * "07_Quick_Delay"�F�f�B���C<BR>
     * ���ύX����K�v���Ȃ��Ǝv���̂ŁA���s�ɍ��킹�鎖�Ƃ���B<BR>
     * �@@�S�̂ƒ�������ہA�ύX���K�v�ƂȂ邩���m��Ȃ��B<BR>
     */
    public String quickDiv;

    /**
     * �������g�p���Ċe��T�[�r�X���{��Ԃ��擾���A�����ɃZ�b�g����B<BR>
     * �iset�T�[�r�X���{��ԃ��\�b�h�����s����j<BR>
     * @@param l_institutionID
     * @@param l_branchID
     * @@param l_accountID
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A337740152
     */
    public WEB3ServiceImpState(
        long l_institutionID,
        long l_branchID,
        long l_accountID)
        throws WEB3BaseException
    {
        setServiceImpState(l_institutionID, l_branchID, l_accountID);
    }

    /**
     * �f�t�H���g�E�R���X�g���N�^<BR>
     * xTrade�ׂ̈Ɏ������Ă������A�ʏ�͈�������̃R���X�g���N�^���g�p����B<BR>
     * @@roseuid 403ED58E0267
     */
    public WEB3ServiceImpState()
    {

    }

    /**
     * �iset�T�[�r�X���{��ԁj
     * �e�T�[�r�X���{��Ԏ擾���\�b�h�����s���A���ʂ𑮐��ɃZ�b�g����B<BR>
     * @@param l_institutionID
     * @@param l_branchID
     * @@param l_accountID
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A3377402A9
     */
    public void setServiceImpState(
        long l_institutionID,
        long l_branchID,
        long l_accountID)
        throws WEB3BaseException
    {
        quickDiv = getQuickDiv(l_accountID);
    }

    /**
     * �iget������񃊃A���f�B���C�敪�j
     * TODO�F�_�~�[�B��Ƀ��A����Ԃ��B�d�l�����܂莟������B<BR>
     * ������񃊃A���f�B���C�敪���擾����B<BR>
     * @@param l_accountID
     * @@return java.lang.String
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A33775000A
     */
    public static String getQuickDiv(long l_accountID) throws WEB3BaseException
    {
        return QUICK_REAL;
    }
}
@
