head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseStatus.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p������Ϗ�ԃN���X �����̌��Ϗ�Ԃ�\������B(WEB3MarginCloseStatus.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 ������ (���u) �V�K�쐬
*/
package webbroker3.equity;


/**
 * �i�M�p������Ϗ�ԁj�B<BR>
 * <BR>
 * �M�p������Ϗ�ԃN���X
 * @@version 1.0
 */
public class WEB3MarginCloseStatus 
{
    
    /**
     * (���ύσt���O)<BR>
     * �������������ύς̏�Ԃ�ێ����Ă���Ȃ��true�B�ȊO�Afalse�B
     */
    public boolean closedMarginFlag;
    
    /**
     * (�����σt���O)<BR>
     * �����������ς̏�Ԃ�ێ����Ă���Ȃ��true�B�ȊO�Afalse�B
     */
    public boolean closeMarginFlag;
    
    /**
     * (���ϒ��t���O)<BR>
     * ���������ϒ��̏�Ԃ�ێ����Ă���Ȃ��true�B�ȊO�Afalse�B
     */
    public boolean closingMarginFlag;
    
    /**
     * (�M�p������Ϗ��)<BR>
     * �R���X�g���N�^�B
     * @@roseuid 40FDCAE50189
     */
    public WEB3MarginCloseStatus() 
    {
     
    }
}
@
