head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.06.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DealingStopDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3DealingStopDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 li-yingyuan(sinocom)�@@�V�K�쐬
*/
package webbroker3.common.define;

/**
 * ������~�敪�@@�萔��`�C���^�t�F�C�X
 *                                                                      
 * @@author li-yingyuan
 * @@version 1.0
 */
public interface WEB3DealingStopDivDef
{
    /**
     * 0 : �����\
     */
    public static final String DEALING_POSSIBLE = "0";

    /**
     * 1 : ������~
     */
    public static final String DEALING_STOP = "1";
    
    /**
     * 2 : ����~
     */
    public static final String SELL_STOP = "2";
    
    /**
     * 3 : ����~
     */
    public static final String BUY_STOP = "3";

}
@
