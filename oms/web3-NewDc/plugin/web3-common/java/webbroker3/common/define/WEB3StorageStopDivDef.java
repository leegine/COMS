head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.59.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3StorageStopDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3StorageStopDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 li-yingyuan(sinocom)�@@�V�K�쐬
*/
package webbroker3.common.define;

/**
 * ���o�ɒ�~�敪�@@�萔��`�C���^�t�F�C�X
 *                                                                      
 * @@author li-yingyuan
 * @@version 1.0
 */
public interface WEB3StorageStopDivDef
{
    /**
     * 0 : ���o�ɉ\
     */
    public static final String STORAGE_POSSIBLE = "0";

    /**
     * 1 : ���o�ɒ�~
     */
    public static final String STORAGE_STOP = "1";
    
    /**
     * 2 : ���ɒ�~
     */
    public static final String STORAGE_IN_STOP = "2";
    
    /**
     * 3 : �o�ɒ�~
     */
    public static final String STORAGE_OUT_STOP = "3";

}
@
