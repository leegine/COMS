head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOtherCountInfomationUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���̑��������(WEB3AioOtherCountInfomationUnit)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/07/11 ��O��(���u) �V�K�쐬
 */

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���̑��������) <BR>
 * ���̑��������N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */

public class WEB3AioOtherCountInfomationUnit extends Message
{
    /**
     * (������) <BR>
     * ������=YYYYMMDD <BR>
     * ���������v�̏ꍇ��YYYYMM
     */
    public String orderBizDate;

    /**
     * (�����P) <BR>
     * ���Z�@@�֌��ϘA�g�̏ꍇ�A�������� <BR>
     * �ב֕ۏ؋��A�O�������i�O���A�g�j�̏ꍇ�A�U�֏o��
     */
    public String buyOrderCount;

    /**
     * (�����Q) <BR>
     * ���Z�@@�֘A�g�̏ꍇ�Anull <BR>
     * �ב֕ۏ؋��A�O�������i�O���A�g�j�̏ꍇ�A�U�֓���
     */
    public String sellOrderCount;

    /**
     * (�����R) <BR>
     * ���Z�@@�֘A�g�̏ꍇ�Anull 
     * �ב֕ۏ؋��A�O�������i�O���A�g�j�̏ꍇ�A�����J�ݐ�
     */
    public String executeCount;

    /**
     * (���̑��������) <BR>
     * �R���X�g���N�^�B
     * 
     * @@roseuid 41B042CE02BD
     */
    public WEB3AioOtherCountInfomationUnit()
    {
    }
}@
