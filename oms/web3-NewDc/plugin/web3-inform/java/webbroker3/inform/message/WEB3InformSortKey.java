head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.49.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �A����񌟍��\�[�g�L�[�N���X(WEB3InformSortKey.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/21 ������(���u) �쐬
*/

package webbroker3.inform.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�A����񌟍��\�[�g�L�[)<BR>
 * �A����񌟍��\�[�g�L�[�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3InformSortKey extends Message
{
    
    /**
     * (�L�[����)<BR>
     * �L�[����<BR>
     * <BR>
     * �E���ʃR�[�h<BR>
     * �E���X�R�[�h<BR>
     * �E�ڋq�R�[�h<BR>
     * �E��t����<BR>
     */
    public String keyItem;
    
    /**
     * (����/�~��)<BR>
     * A�F ����<BR>
     * D�F �~��<BR>
     */
    public String ascDesc;
    
    /**
     * @@roseuid 41EE625E0399
     */
    public WEB3InformSortKey() 
    {
     
    }
}
@
