head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������t�敪�萔��`�C���^�t�F�C�X(WEB3FeqOrderAcceptTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 �Г� (���u) �V�K�쐬
*/
package webbroker3.feq.define;

/**
 * ������t�敪�萔��`�C���^�t�F�C�X
 *
 * @@author �Г�
 * @@version 1.0
 */
public interface WEB3FeqOrderAcceptTypeDef
{
    /**
     * 0:��t����
     */
    public final static String EXEC_TYPE_NAN = "0";
    
    /**
     * 1:��t��
     */
    public final static String EXEC_TYPE_NOT_NAN = "1";
    
    /**
     * 2:��t�G���[
     */
    public final static String EXEC_TYPE_ERROR = "2";

    /**
     * 9�F�����E������ꂽ�f�[�^
     */
    public final static String CNANGE_CANCELED_DATA = "9";
}
@
