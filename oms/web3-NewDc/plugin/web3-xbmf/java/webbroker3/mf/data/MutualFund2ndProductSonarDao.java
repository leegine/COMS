head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFund2ndProductSonarDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.mf.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link MutualFund2ndProductSonarDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link MutualFund2ndProductSonarRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFund2ndProductSonarPK 
 * @@see MutualFund2ndProductSonarRow 
 */
public class MutualFund2ndProductSonarDao extends DataAccessObject {


  /** 
   * ����{@@link MutualFund2ndProductSonarDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private MutualFund2ndProductSonarRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link MutualFund2ndProductSonarRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link MutualFund2ndProductSonarDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link MutualFund2ndProductSonarDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link MutualFund2ndProductSonarRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MutualFund2ndProductSonarRow )
                return new MutualFund2ndProductSonarDao( (MutualFund2ndProductSonarRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MutualFund2ndProductSonarRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MutualFund2ndProductSonarRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link MutualFund2ndProductSonarRow}�I�u�W�F�N�g 
    */
    protected MutualFund2ndProductSonarDao( MutualFund2ndProductSonarRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link MutualFund2ndProductSonarRow}�I�u�W�F�N�g���擾���܂��B
   */
    public MutualFund2ndProductSonarRow getMutualFund2ndProductSonarRow() {
        return row;
    }


  /** 
   * �w���{@@link MutualFund2ndProductSonarRow}�I�u�W�F�N�g����{@@link MutualFund2ndProductSonarDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link MutualFund2ndProductSonarRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link MutualFund2ndProductSonarDao}�擾�̂��߂Ɏw���{@@link MutualFund2ndProductSonarRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link MutualFund2ndProductSonarDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static MutualFund2ndProductSonarDao forRow( MutualFund2ndProductSonarRow row ) throws java.lang.IllegalArgumentException {
        return (MutualFund2ndProductSonarDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MutualFund2ndProductSonarRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link MutualFund2ndProductSonarRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link MutualFund2ndProductSonarPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link MutualFund2ndProductSonarParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MutualFund2ndProductSonarRow.TYPE );
    }


  /** 
   * {@@link MutualFund2ndProductSonarRow}����ӂɓ��肷��{@@link MutualFund2ndProductSonarPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link MutualFund2ndProductSonarRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link MutualFund2ndProductSonarParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link MutualFund2ndProductSonarPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static MutualFund2ndProductSonarPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link MutualFund2ndProductSonarRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MutualFund2ndProductSonarRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MutualFund2ndProductSonarRow findRowByPk( String p_productCode, String p_institutionCode ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFund2ndProductSonarPK pk = new MutualFund2ndProductSonarPK( p_productCode, p_institutionCode );
        return findRowByPk( pk );
    }


  /** 
   * �w���MutualFund2ndProductSonarPK�I�u�W�F�N�g����{@@link MutualFund2ndProductSonarRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����MutualFund2ndProductSonarPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MutualFund2ndProductSonarRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MutualFund2ndProductSonarRow findRowByPk( MutualFund2ndProductSonarPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MutualFund2ndProductSonarRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String)}�����{@@link #forRow(MutualFund2ndProductSonarRow)}���g�p���Ă��������B 
   */
    public static MutualFund2ndProductSonarDao findDaoByPk( String p_productCode, String p_institutionCode ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFund2ndProductSonarPK pk = new MutualFund2ndProductSonarPK( p_productCode, p_institutionCode );
        MutualFund2ndProductSonarRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(MutualFund2ndProductSonarPK)}�����{@@link #forRow(MutualFund2ndProductSonarRow)}���g�p���Ă��������B 
   */
    public static MutualFund2ndProductSonarDao findDaoByPk( MutualFund2ndProductSonarPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFund2ndProductSonarRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_productCode, p_institutionCode, and �ɂĎw��̒l�����ӂ�{@@link MutualFund2ndProductSonarRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_productCode, p_institutionCode, and �̒l�ƈ�v����{@@link MutualFund2ndProductSonarRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static MutualFund2ndProductSonarRow findRowByProductCodeInstitutionCode( String p_productCode, String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MutualFund2ndProductSonarRow.TYPE,
            "product_code=? and institution_code=?",
            null,
            new Object[] { p_productCode, p_institutionCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MutualFund2ndProductSonarRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MutualFund2ndProductSonarDao.findRowsByProductCodeInstitutionCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByProductCodeInstitutionCode(String, String)}�����{@@link #forRow(MutualFund2ndProductSonarRow)}���g�p���Ă��������B 
   */
    public static MutualFund2ndProductSonarDao findDaoByProductCodeInstitutionCode( String p_productCode, String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductCodeInstitutionCode( p_productCode, p_institutionCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
