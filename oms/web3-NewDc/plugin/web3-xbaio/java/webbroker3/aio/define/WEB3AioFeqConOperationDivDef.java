head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.45.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioFeqConOperationDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3AioFeqConOperationDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/23 ���E (���u) �V�K�쐬
*/
package webbroker3.aio.define;

/**
 * �O�������ւ̐U�֎���T�[�r�XImpl��get�����敪�̖߂�l�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author ���E
 * @@version 1.0
 */
public interface WEB3AioFeqConOperationDivDef
{
    /**
     * 0 : �U�֒�
     */
    public final static String TRANSFERING = "0";
    
    /**
     * 1 : UWG���ϒ�
     */
    public final static String UWG_TRANSFERING = "1";
    
    /**
     * 2 : UWG���ϊ���
     */
    public final static String UWG_TRANSFER_COMPLETE = "2";

    /**
     * 3 : UWG���σG���[
     */
    public final static String UWG_TRANSFER_ERROE = "3";
    
    /**
     * 4 : �����
     */
    public final static String CANCELED = "4";

}
@
