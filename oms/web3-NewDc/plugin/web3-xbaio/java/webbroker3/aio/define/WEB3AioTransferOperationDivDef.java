head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.45.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioTransferOperationDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3AioTransferOperationDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 ���� (���u) �V�K�쐬
                 : 2006/08/04 ��� (SCS)���f���@@595�Ή�
*/

package webbroker3.aio.define;

/**
 * FX�U�֖��� �̐U�֋敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author ����
 * @@version 1.0
 */

public interface WEB3AioTransferOperationDivDef
{
    /**
     * 1�F�����iFX�j 
     */
    public static final String CASH_IN = "1";
    
    /**
     * 2�F�o���iFX�j
     */
    public static final String CASH_OUT = "2";

    /**
     * 3�F�o���i��OP�j
     */
    public static final String FUOP_OUT = "3";

    /**
     * 4�F�����i��OP�j
     */
    public static final String FUOP_IN = "4";
}
@
