head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.48.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondCustodianUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �J�X�g�f�B�A��(WEB3AdminBondCustodianUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�J�X�g�f�B�A��)<BR>
 * �J�X�g�f�B�A��
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondCustodianUnit  extends Message
{
    
    /**
     * (�J�X�g�f�B�A���R�[�h)<BR>
     * �J�X�g�f�B�A���R�[�h
     */
    public String custodianCode;
    
    /**
     * (�J�X�g�f�B�A������)<BR>
     * �J�X�g�f�B�A������
     */
    public String custodianName;
    
    /**
     * @@roseuid 44E3363202CE
     */
    public WEB3AdminBondCustodianUnit() 
    {
     
    }
}
@
