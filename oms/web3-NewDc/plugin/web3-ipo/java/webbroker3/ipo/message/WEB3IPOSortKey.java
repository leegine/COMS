head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\�[�g�L�[���b�Z�[�W�f�[�^(WEB3IPOSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * IPO�\�[�g�L�[���b�Z�[�W�f�[�^�N���X
 *                                                                   
 * @@author ����
 * @@version 1.0
 */
public class WEB3IPOSortKey extends Message
{
    
    /**
     * �L�[����
     */
    public String keyItem;
    
    /**
     * �����^�~��<BR>
     * <BR>
     * �`�F ����<BR>
     * �c�F �~��<BR>
     */
    public String ascDesc;
    
    /**
     * @@roseuid 4112E4E1037D
     */
    public WEB3IPOSortKey() 
    {
     
    }
}
@
