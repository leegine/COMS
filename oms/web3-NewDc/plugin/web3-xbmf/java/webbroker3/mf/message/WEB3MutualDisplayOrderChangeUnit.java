head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.02.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualDisplayOrderChangeUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��Җ����\�������X�V�l(WEB3MutualDisplayOrderChangeUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 ���� (���u) �V�K�쐬 
*/

package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���M�Ǘ��Җ����\�������X�V�l)<BR>
 * �����M���Ǘ��Җ����\�������X�V�l�f�[�^�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualDisplayOrderChangeUnit extends Message 
{
    /**
     * (�\����)<BR>
     *  �\����
     */
    public String displayOrder;
    
    /**
     * (�����R�[�h)<BR>
     *  �����R�[�h
     */
    public String mutualProductCode;
    
    /**
     * (���M�Ǘ��Җ����\�������X�V�l)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 4153CDE60382
     */
    public WEB3MutualDisplayOrderChangeUnit()
    {
    }
}
@
