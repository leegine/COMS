head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : PTS�\���q���(WEB3AdminInformPTSAccountInfoUnit.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 �đo�g(���u) �V�K�쐬 ���f��No.128
*/

package webbroker3.inform.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * PTS�\���q���<BR>
 * PTS�\���q���N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountInfoUnit extends Message
{

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;

    /**
     * (�ڋq��)<BR>
     * �ڋq��
     */
    public String accountName;

    /**
     * (�\���敪)<BR>
     * �\���敪<BR>
     * <BR>
     * 0�F���J��<BR>
     * 1�F�J��<BR>
     */
    public String ptsAccOpenDiv;

    /**
     * (���)<BR>
     * ���<BR>
     * <BR>
     * 0�F����<BR>
     * 1�F�L��<BR>
     */
    public String status;

    /**
     * (�\������)<BR>
     * �\������
     */
    public Date applyDate;

    /**
     * (�X�V��)<BR>
     * �X�V��
     */
    public String lastUpdater;

    /**
     * �R���X�g���N�^�B
     * @@roseuid 47BA5CB00375
     */
    public WEB3AdminInformPTSAccountInfoUnit()
    {

    }
}
@
