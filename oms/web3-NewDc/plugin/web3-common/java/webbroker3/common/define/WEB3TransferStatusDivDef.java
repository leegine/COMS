head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.49.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TransferStatusDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3TransferStatusDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 ���z (���u) �V�K�쐬
*/

package webbroker3.common.define;

/**
 * �U�֏󋵋敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author ���z
 * @@version 1.0
 */
public interface WEB3TransferStatusDivDef
{
    /**
     * 0�F���ϒ��@@�@@
     */
    public static final String PROCESSING = "0";
    
    /**
     * 1�F���ϊ���
     */
    public static final String PROCESS_COMPLETE = "1";
    
    /**
     * 2�F���σG���[
     */
    public static final String PROCESS_ERROR = "2";
    
    /**
     * 3�F���
     */
    public static final String CANCEL = "3";
    
    /**
     * 5�F���̑�
     */
    public static final String OTHER = "5";
}
@
