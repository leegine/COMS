head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsExecuteUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����������Ɖ���(WEB3OptionsExecuteUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 羉s (���u) �V�K�쐬
              001: 2004/07/28 ���Ō� (���u) �Ή� �ڍא݌v�`�F�b�N�w�E���� (���{��) 
                   com.fitechlabs.xtrade.kernel.message.Message���p���B
*/

package webbroker3.ifo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����w���I�v�V�����������Ɖ���)<BR>
 * �����w���I�v�V�����������Ɖ���N���X<BR>                                                                    
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3OptionsExecuteUnit extends Message
{

    /**
     * ������
     */
    public Date executionTimestamp;

    /**
     * ��萔��
     */
    public String execQuantity;

    /**
     * ���P��
     */
    public String execPrice;

    /**
     * @@roseuid 40C0754B037A
     */
    public WEB3OptionsExecuteUnit()
    {

    }
}
@
