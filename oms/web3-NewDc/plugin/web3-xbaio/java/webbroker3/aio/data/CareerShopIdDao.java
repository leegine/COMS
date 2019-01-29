head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.48.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	CareerShopIdDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link CareerShopIdDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link CareerShopIdRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see CareerShopIdPK 
 * @@see CareerShopIdRow 
 */
public class CareerShopIdDao extends DataAccessObject {


  /** 
   * ����{@@link CareerShopIdDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private CareerShopIdRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link CareerShopIdRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link CareerShopIdDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link CareerShopIdDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link CareerShopIdRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CareerShopIdRow )
                return new CareerShopIdDao( (CareerShopIdRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CareerShopIdRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CareerShopIdRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link CareerShopIdRow}�I�u�W�F�N�g 
    */
    protected CareerShopIdDao( CareerShopIdRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link CareerShopIdRow}�I�u�W�F�N�g���擾���܂��B
   */
    public CareerShopIdRow getCareerShopIdRow() {
        return row;
    }


  /** 
   * �w���{@@link CareerShopIdRow}�I�u�W�F�N�g����{@@link CareerShopIdDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link CareerShopIdRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link CareerShopIdDao}�擾�̂��߂Ɏw���{@@link CareerShopIdRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link CareerShopIdDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static CareerShopIdDao forRow( CareerShopIdRow row ) throws java.lang.IllegalArgumentException {
        return (CareerShopIdDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CareerShopIdRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link CareerShopIdRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link CareerShopIdPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link CareerShopIdParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CareerShopIdRow.TYPE );
    }


  /** 
   * {@@link CareerShopIdRow}����ӂɓ��肷��{@@link CareerShopIdPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link CareerShopIdRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link CareerShopIdParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link CareerShopIdPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static CareerShopIdPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link CareerShopIdRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_careerDiv �����Ώۂł���p_careerDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CareerShopIdRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CareerShopIdRow findRowByPk( String p_institutionCode, String p_branchCode, String p_careerDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        CareerShopIdPK pk = new CareerShopIdPK( p_institutionCode, p_branchCode, p_careerDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���CareerShopIdPK�I�u�W�F�N�g����{@@link CareerShopIdRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����CareerShopIdPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CareerShopIdRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CareerShopIdRow findRowByPk( CareerShopIdPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CareerShopIdRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String)}�����{@@link #forRow(CareerShopIdRow)}���g�p���Ă��������B 
   */
    public static CareerShopIdDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_careerDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        CareerShopIdPK pk = new CareerShopIdPK( p_institutionCode, p_branchCode, p_careerDiv );
        CareerShopIdRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(CareerShopIdPK)}�����{@@link #forRow(CareerShopIdRow)}���g�p���Ă��������B 
   */
    public static CareerShopIdDao findDaoByPk( CareerShopIdPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CareerShopIdRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
