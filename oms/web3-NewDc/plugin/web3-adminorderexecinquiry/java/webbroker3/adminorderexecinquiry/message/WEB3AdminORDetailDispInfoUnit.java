head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORDetailDispInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڍ׉�ʏ�� (WEB3AdminORDetailDispInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�ڍ׉�ʏ��)<BR>
 * <BR>
 * �ڍ׉�ʏ��N���X<BR>
 * <BR>
 * �{�N���X�̏����Z�b�V�����ŕێ����A�ڍ׉�ʂɕ\������B<BR>
 * <BR>
 * -----<English>---------------<BR>
 * <BR>
 * WEB3AdminORDetailDispInfoUnit class <BR>
 * <BR>
 * Save the information of this class in the session and display details <BR>
 * on the screen<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminORDetailDispInfoUnit extends Message
{

    /**
     * (����ID)<BR>
     * <BR>
     * ����ID<BR>
     * <BR>
     * orderId<BR>
     * <BR>
     */
    public String orderId;

    /**
     * (���i�敪)<BR>
     * <BR>
     * ���i�敪<BR>
     * <BR>
     * 0�F�@@��������<BR>
     * 1�F�@@�M�p���<BR>
     * 2�F�@@�����~�j����<BR>
     * 3�F�@@�I�v�V����<BR>
     * 4�F�@@�敨<BR>
     * 5�F�@@���M<BR>
     * 6�F�@@�ݓ�<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * productDiv<BR>
     * 0: Def.EQUITY<BR>
     * 1: Def.MARGIN<BR>
     * 2: Def.MINI_STOCK<BR>
     * 3: Def.OPTION<BR>
     * 4: Def.FUTURE<BR>
     * 5: Def.MF<BR>
     * 6: Def.RUITO<BR>
     * <BR>
     */
    public String productDiv;

    /**
     * (����ID)<BR>
     * <BR>
     * ����ID<BR>
     */
    public String accountID;
    
    /**
     * @@roseuid 4212FD6101EC
     */
    public WEB3AdminORDetailDispInfoUnit()
    {

    }
}
@
