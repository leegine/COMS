head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoProductCodeNameUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ��������ꗗ�p�f�[�^�N���X(WEB3RuitoProductCodeName)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 �� �E (���u) �V�K�쐬
                   2004/12/03 ��O�� (���u) �c�Ή�
*/
package webbroker3.xbruito.message;

import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * �ݐϓ��������ꗗ�p�f�[�^�N���X
 */
public class WEB3RuitoProductCodeNameUnit extends Message
{

    /**
     * �������ɑΉ����������R�[�h
     */
    public String ruitoProductCode;

    /**
     * �����R�[�h�ɑΉ�����������
     */
    public String ruitoProductName;

    /**
     * ���t�\�敪
     * null�F����\ 
     * �P�F�V�X�e�������~�G���[ 
     * �Q�F��t���ԃG���[ 
     * �R�F�����~�� 
     * �S�F�ً}��~��
     */
    public String buyPosDiv;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40C96B670138
     */
    public WEB3RuitoProductCodeNameUnit()
    {

    }
}
@
